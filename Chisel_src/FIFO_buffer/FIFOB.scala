package FIFOB

import chisel3._

class writeSide(size: Int) extends Bundle { //size: Int class parameter
    val write = Input(Bool())
    val full = Output(Bool())                 //type declaration, doesn't have memory to hold calue
    val din = Input(UInt(size.W))
}

class readSide(size: Int) extends Bundle{
    val read = Input(Bool())
    val empty = Output(Bool())
    val dout = Output(UInt(size.W))
}

class FIFORegister(size: Int) extends Module{
    
    // You must encapsulate all the variable into Reg wire or IO, 
    // or they will not be converted in to hardware
    val io = IO(new Bundle{                 //IO(new ..) is converting sth into IO type
        val enq = new writeSide(size)       
        val deq = new readSide(size)
    })

    val dataReg = RegInit(0.U(size.W))
    val stateReg = RegInit(false.B)          // false empty, true full 
    
    io.enq.full := false.B
    io.deq.empty := true.B
    io.deq.dout := 0.U

    when(stateReg === false.B){             // empty then write
        printf("the buffer is empty\n")
        when (io.enq.write === true.B){
            dataReg := io.enq.din
            stateReg := true.B
        }   
    } .otherwise{                           // full then read
        printf("the buffer is full\n")
        when (io.deq.read === true.B){
            stateReg := false.B
            // io.deq.dout := dataReg
        }
    }

    // At last, send the outer read/write part signal to stop read/write
    io.deq.dout := dataReg                  //no matter read or not dout is dataReg
    io.deq.empty := stateReg === false.B
    io.enq.full := stateReg === true.B
    
}

object FIFOSigR extends App{
    // emitVerilog(new FIFORegister(1),Array("--target-dir","generated"))
    emitVerilog(new FIFORegister(1),Array("--target-dir","generated"))
}