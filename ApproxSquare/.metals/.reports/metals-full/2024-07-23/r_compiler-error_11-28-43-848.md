file://<HOME>/Learning%20Material/2024.6-8-Summer-Research-in-ZhuoC-Group/ApproxSquare/src/main/scala/ImplementPkg/exp_prcss.scala
### java.lang.IndexOutOfBoundsException: 1

occurred in the presentation compiler.

presentation compiler configuration:
Scala version: 3.3.3
Classpath:
<HOME>/Library/Caches/Coursier/v1/https/maven.aliyun.com/repository/central/org/scala-lang/scala3-library_3/3.3.3/scala3-library_3-3.3.3.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/maven.aliyun.com/repository/central/org/scala-lang/scala-library/2.13.12/scala-library-2.13.12.jar [exists ]
Options:



action parameters:
offset: 2571
uri: file://<HOME>/Learning%20Material/2024.6-8-Summer-Research-in-ZhuoC-Group/ApproxSquare/src/main/scala/ImplementPkg/exp_prcss.scala
text:
```scala
package ImplementPkg

// In this module, we should first turn the exp bit into real exponential
// Then add them up, add 127 [the offset] to formalize the result.

// Also, whether or not the input is a normalised number is important in this module.

import chisel3._
import chisel3.util._
import javax.swing.InputMap

class exp_prcss(size: Int = 8,man_size: Int = 23) extends Module{
	val exp_io = IO (new Bundle{
		val x_exp = Input(UInt(size.W))
		val y_exp = Input(UInt(size.W))
		val x_man = Input(UInt(man_size.W))
		val y_man = Input(UInt(man_size.W))
		val out_exp = Output(UInt(size.W))
		val isDenormal = Outout(Bool())
		val out_man = Output(UInt(man_size.W))
		
	})

	val bias = 127.U(size.W)
	val x_isDenormal = (x_exp === 0.U && x_man =/= 0.U)
	val y_isDenormal = (y_exp === 0.U && y_man =/= 0.U)
	val x_isNaN = (x_exp === Fill(y_exp.getWidth, "b1".U) && x_man =/= 0.U)
	val y_isNan = (y_exp === Fill(y_exp.getWidth, "b1".U) && y_man =/= 0.U)
	val x_isInf = (x_exp === Fill(y_exp.getWidth, "b1".U) && x_man === 0.U)
	val y_isInf = (y_exp === Fill(y_exp.getWidth, "b1".U) && y_man === 0.U)
	val x_isZero = (x_exp === 0.U && x_man === 0.U)
	val y_isZero = (x_exp === 0.U && x_man === 0.U)
	val expSum = x_exp + y_exp - bias

	when (x_isNaN || y_isNan){
		exp_io.out_exp = Fill(y_exp.getWidth, "b1".U)
	}
	.elsewhen (x_isInf || y_isInf){					//if x,y contain at least one Infinite number
		when (x_isInf ^ y_isInf === 0.U){			//Both are inf
			exp_io.out_exp = Fill(y_exp.getWidth, "b1".U)
			exp_io.out_man = Fill(y_man.getWidth, "b0".U)
		}
		.elsewhen (x_isZero ^ y_isZero === 1.U){	// 1 inf 1 Zero, result is Nan
			exp_io.out_exp = Fill(y_exp.getWidth, "b1".U)
			// the output mantissa is left unchanged
		}								
		.elsewhen(x_isDenormal ^ y_isDenormal === 1.U){  //1 Inf 1 Denormal, result is Inf
			exp_io.out_exp = Fill(y_exp.getWidth, "b1".U)
			exp_io.out_man = Fill(y_man.getWidth, "b0".U)
		}
		.elsewhen (x_isDenormal ^ y_isDenormal === 0.U){  //1 Inf 1 Normal, result is Inf
			exp_io.out_exp = Fill(y_exp.getWidth, "b1".U)
			exp_io.out_man = Fill(y_man.getWidth, "b0".U)
		} 
	}
	.elsewhen (x_isZero || y_isZero){				// No Inf No Nan, if contains at least one zero, the result is zero
		exp_io.out_exp = Fill(y_exp.getWidth, "b0".U)
		exp_io.out_man = Fill(y_man.getWidth, "b0".U)
	}	
	.elsewhen (x_isDenormal || y_isDenormal){							//No Inf, NaN or Zero, if contains at least one Denormal.
		when (x_isDenormal ^ y_isDenormal === 1.U)						//Only one denormal
		{

		}
		.elsewhen (x_isDenormal ^ y_isDenormal === 0.U){				//Two denormal,@@

		}
	}								
	

	
	


	
}  

```



#### Error stacktrace:

```
scala.collection.LinearSeqOps.apply(LinearSeq.scala:131)
	scala.collection.LinearSeqOps.apply$(LinearSeq.scala:128)
	scala.collection.immutable.List.apply(List.scala:79)
	dotty.tools.dotc.util.Signatures$.countParams(Signatures.scala:501)
	dotty.tools.dotc.util.Signatures$.countParams(Signatures.scala:501)
	dotty.tools.dotc.util.Signatures$.applyCallInfo(Signatures.scala:186)
	dotty.tools.dotc.util.Signatures$.computeSignatureHelp(Signatures.scala:94)
	dotty.tools.dotc.util.Signatures$.signatureHelp(Signatures.scala:63)
	scala.meta.internal.pc.MetalsSignatures$.signatures(MetalsSignatures.scala:17)
	scala.meta.internal.pc.SignatureHelpProvider$.signatureHelp(SignatureHelpProvider.scala:51)
	scala.meta.internal.pc.ScalaPresentationCompiler.signatureHelp$$anonfun$1(ScalaPresentationCompiler.scala:426)
```
#### Short summary: 

java.lang.IndexOutOfBoundsException: 1