package ImplementPkg

import chisel3._
import chisel3.util._

// This module is used to realize the PAM design

class BasicTree(Inputnum : Int) extends Module{
    val Basic_io = IO(new Bundle{
        val inputs = Input(Vec(Inputnum,UInt(1.W)))
        val output = Output(Vec(Inputnum/ 3 * 2,UInt(1.W)))
    })

    Basic_io.output := 0.U

    for (i <- 0 until Inputnum) {
        when (activeInputs(i)){
            Basic_io.output := Basic_io.output + Basic_io.inputs(i) 
        }
        .otherwise{
            Basic_io.output := Basic_io.output + 0.U
        }
    }
    
}



class manPrc(man_size : Int = 23) extends Module {
    val man_io = IO(new Bundle{
        val x_man = Input(UInt(man_size.W))
        val y_man = Input(UInt(man_size.W))
        val n = Input(UInt(4.W))                    // This is used for representing the approxmation level
        val out_man = Output(UInt(man_size.W))
    })



}


