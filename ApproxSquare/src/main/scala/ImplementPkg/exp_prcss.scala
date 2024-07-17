package ImplementPkg

// In this module, we should first turn the exp bit into real exponential
// Then add them up, add 127 [the offset] to formalize the result.

import chisel3._
import chisel3.util._

class exp_prcss(size: Int = 8) extends Module{
	val exp_io = IO (new Bundle{
		val x_exp = Input(UInt(size.W))
		val y_exp = Input(UInt(size.W))
		val out_exp = Output(UInt(size.W))
	})
	exp_io.out_exp := (exp_io.x_exp - 127.U) + (exp_io.y_exp - 127.U) + 127.U
}  
