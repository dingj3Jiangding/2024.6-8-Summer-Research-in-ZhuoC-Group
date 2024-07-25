package ImplementPkg

import chisel3._
import chisel3.util._

class mux_3_1 extends Module {
    val mux_io = IO(new Bundle{
        val sel = Input(UInt(2.W))
        val in0 = Input(UInt(1.W))
        val in1 = Input(UInt(1.W))
        val in2 = Input(UInt(1.W))
        val out = Output(UInt(1.W))
        
    })

    mux_io.out := Mux (mux_io.sel === 0.U, mux_io.in0, 
                      (Mux(mux_io.sel === 1.U, mux_io.in1, mux_io.in2)))
}
