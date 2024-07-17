package ImplementPkg

import chisel3._
import chisel3.util._

// This module is used for processing the sign bit of the 2 Input of the PAM
// In the Approx_Square, this module is not needed, since the sign is fixed

class sign_prcss extends Module {
    val sg_io = IO(new Bundle{
        val x_sg = Input(UInt(1.W))
        val y_sg = Input(UInt(1.W))
        val output_sg = Output(UInt(1.W))
    })

    sg_io.output_sg := sg_io.x_sg ^ sg_io.y_sg
}