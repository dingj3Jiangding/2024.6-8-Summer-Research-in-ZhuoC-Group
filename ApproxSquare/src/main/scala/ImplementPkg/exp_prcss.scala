package ImplementPkg

// In this module, we should first turn the exp bit into real exponential
// Then add them up, add 127 [the offset] to formalize the result.

// Also, whether or not the input is a normalised number is important in this module.

import chisel3._
import chisel3.util._

class exp_prcss(size: Int = 8, man_size: Int = 23) extends Module{
	val exp_io = IO (new Bundle{
		val x_exp = Input(UInt(size.W))
		val y_exp = Input(UInt(size.W))
		val x_man = Input(UInt(man_size.W))
		val y_man = Input(UInt(man_size.W))
		val out_exp = Output(UInt(size.W))
		val isDenormal = Output(Bool())
		val out_man = Output(UInt(man_size.W))
		val isman = Output(Bool())
		val branch_number = Output(UInt(size.W))
	})

	exp_io.out_exp := 0.U
  	exp_io.out_man := 0.U
 	exp_io.isDenormal := false.B
	exp_io.isman := false.B
	exp_io.branch_number := 0.U
	
	val bias = 127.U(size.W)
	val x_isDenormal = (exp_io.x_exp === 0.U && exp_io.x_man =/= 0.U)
	val y_isDenormal = (exp_io.y_exp === 0.U && exp_io.y_man =/= 0.U)
	val x_isNaN = (exp_io.x_exp === Fill(exp_io.y_exp.getWidth, "b1".U) && exp_io.x_man =/= 0.U)
	val y_isNaN = (exp_io.y_exp === Fill(exp_io.y_exp.getWidth, "b1".U) && exp_io.y_man =/= 0.U)
	val x_isInf = (exp_io.x_exp === Fill(exp_io.y_exp.getWidth, "b1".U) && exp_io.x_man === 0.U)
	val y_isInf = (exp_io.y_exp === Fill(exp_io.y_exp.getWidth, "b1".U) && exp_io.y_man === 0.U)
	val x_isZero = (exp_io.x_exp === 0.U && exp_io.x_man === 0.U)
	val y_isZero = (exp_io.x_exp === 0.U && exp_io.x_man === 0.U)
	
	// val expSum = exp_io.x_exp + exp_io.y_exp - bias
	
	// Extends expSum to be a 9-bit signed Int
	val x_exp_signed = Cat(0.U(1.W), exp_io.x_exp).asSInt
  	val y_exp_signed = Cat(0.U(1.W), exp_io.y_exp).asSInt
  	val bias_signed = Cat(0.U(1.W), bias).asSInt

	val expSum = (x_exp_signed + y_exp_signed - bias_signed).asSInt

	when (x_isNaN || y_isNaN){
		exp_io.out_exp := Fill(exp_io.y_exp.getWidth, "b1".U)
		exp_io.branch_number := 1.U
	}
	.elsewhen (x_isInf || y_isInf){					//if x,y contain at least one Infinite number
		when (x_isInf ^ y_isInf === 0.U){			//Both are inf
			exp_io.out_exp := Fill(exp_io.y_exp.getWidth, "b1".U)
			exp_io.out_man := Fill(exp_io.y_man.getWidth, "b0".U)
			exp_io.isman := true.B
			exp_io.branch_number := 2.U
		}
		.elsewhen (x_isZero ^ y_isZero === 1.U){	// 1 inf 1 Zero, result is Nan
			exp_io.out_exp := Fill(exp_io.y_exp.getWidth, "b1".U)
			exp_io.branch_number := 3.U
			// the output mantissa is left unchanged
		}								
		.elsewhen(x_isDenormal ^ y_isDenormal === 1.U){  //1 Inf 1 Denormal, result is Inf
			exp_io.out_exp := Fill(exp_io.y_exp.getWidth, "b1".U)
			exp_io.out_man := Fill(exp_io.y_man.getWidth, "b0".U)
			exp_io.isman := true.B
			exp_io.branch_number := 4.U
		}
		.elsewhen (x_isDenormal ^ y_isDenormal === 0.U){  //1 Inf 1 Normal, result is Inf
			exp_io.out_exp := Fill(exp_io.y_exp.getWidth, "b1".U)
			exp_io.out_man := Fill(exp_io.y_man.getWidth, "b0".U)
			exp_io.isman := true.B
			exp_io.branch_number := 5.U
		} 
	}
	.elsewhen (x_isZero || y_isZero){				// No Inf No Nan, if contains at least one zero, the result is zero
		exp_io.out_exp := Fill(exp_io.y_exp.getWidth, "b0".U)
		exp_io.out_man := Fill(exp_io.y_man.getWidth, "b0".U)
		exp_io.isman := true.B
		exp_io.branch_number := 6.U
	}	
	.elsewhen (x_isDenormal || y_isDenormal){							//No Inf, NaN or Zero, if contains at least one Denormal.
		when (x_isDenormal ^ y_isDenormal === 1.U)						//Only one denormal, the result is expsum + 1
		{
			val temp_exp = expSum + 1.S
			when(temp_exp > (Cat(0.U(1.W), Fill(exp_io.y_exp.getWidth,"b1".U)).asSInt - 1.S)){		//Overflow
				exp_io.out_exp := Fill(exp_io.y_exp.getWidth,"b1".U)
				exp_io.out_man := 0.U
				exp_io.isman := true.B
				exp_io.branch_number := 7.U
			}
			.elsewhen(temp_exp < 1.S){										// when underflow, the exp should be set to the min normal exp
				exp_io.out_exp := Fill(exp_io.y_exp.getWidth, "b0".U)
				exp_io.isDenormal := true.B
				exp_io.branch_number := 8.U
			}
			.otherwise{
				val temp_exp_unsigned = temp_exp.asUInt 
				exp_io.out_exp := temp_exp_unsigned(size - 1,0)
				exp_io.branch_number := 9.U
			}
		}
		.elsewhen (x_isDenormal ^ y_isDenormal === 0.U){				//Two denormal, the result is 0
			exp_io.out_exp := Fill(exp_io.y_exp.getWidth, "b0".U)
			exp_io.out_man := Fill(exp_io.y_man.getWidth, "b0".U)
			exp_io.isman := true.B
			exp_io.branch_number := 10.U
		}
	}
	.elsewhen (x_isDenormal ^ y_isDenormal === 0.U) {					// No denormal and the denormal number is even, so there are two normal number
		val temp_exp = expSum
		when(temp_exp > (Cat(0.U(1.W), Fill(exp_io.y_exp.getWidth,"b1".U)).asSInt - 1.S)){		//Overflow
			exp_io.out_exp := Fill(exp_io.y_exp.getWidth,"b1".U)
			exp_io.out_man := 0.U
			exp_io.isman := true.B
			exp_io.branch_number := 11.U
		}
		.elsewhen(temp_exp < 1.S){										// when underflow, the exp should be set to the min normal exp
			exp_io.out_exp := Fill(exp_io.y_exp.getWidth, "b0".U)
			exp_io.isDenormal := true.B
			exp_io.branch_number := 12.U
		}
		.otherwise{														// Normal Input & Normal Output
			val temp_exp_unsigned = temp_exp.asUInt 
			exp_io.out_exp := temp_exp_unsigned(size - 1,0)
			exp_io.branch_number := 13.U
		}
	}
	.otherwise{
		exp_io.branch_number := 100.U
	}
}  
