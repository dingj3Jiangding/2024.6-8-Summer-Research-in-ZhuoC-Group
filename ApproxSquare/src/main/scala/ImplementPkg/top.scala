package ImplementPkg

import chisel3._
import chisel3.util._

class Top(Inputnum : Int, levels : Int) extends Module{
    val top_io = IO(new Bundle{
        val inputs = Inputs(Vec(Inputnum, UInt(32.W)))
        val outputs = Output(Vec(levels,UInt(32.W)))
    })

    val levelActiveInputs: Seq[Seq[Boolean]] = Seq(
        Seq(true,true,true,true,true),  //Level 0: 5 pp
        Seq(true,true),
        Seq(true,true),
        Seq(true,true),
        Seq(true,true),
        Seq(true,true),                 //Level 5
        Seq(true,true),
        Seq(true,true),
        Seq(true,true),
        Seq(true,true),
        Seq(true,true),                 //Level 10
        Seq(true,true),                 //Level 11
    )
    
    val compressors = LevelActiveInputs.map( activeInputs =>
        Module(new LevelCompressor(activeInputs.length, activeInputs))
    )

    for ((compressor, level) <- compressors.zipWithIndex){
        val activeCount = levelActiveInputs(level).count(_ == true)
        compressor.Basic_io.inputs := top_io.inputs.take(activeCount)
        val controlInputs := inputs.map(input =>
            MUX(levelIndex.U <= top_io.level,input, 0.U)
        )
        compressor.Basic_io.inputs := controlInputs
        top_io.outputs(level) := compressor.Basic_io.output
    }
}
