package ImplementPkg

import chisel3._
import chisel3.util._ 
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class mux_3_1Test extends AnyFlatSpec with ChiselScalatestTester{
    "Mux_3_1_a" should "pass" in{
        test (new mux_3_1){ dut =>
            dut.mux_io.sel.poke(0.U)
            dut.mux_io.in0.poke(1.U)
            dut.mux_io.in1.poke(0.U)
            dut.mux_io.in2.poke(0.U)

            dut.clock.step()
            println("What's the output? The output is ",dut.mux_io.out.peek().litValue)
            dut.mux_io.out.expect(1.U,"the output should be 1")

            dut.mux_io.sel.poke(0.U)
            dut.mux_io.in0.poke(0.U)
            dut.mux_io.in1.poke(1.U)
            dut.mux_io.in2.poke(1.U)

            dut.clock.step()
            println("What's the output? The output is ",dut.mux_io.out.peek().litValue)
            dut.mux_io.out.expect(0.U,"the output should be 0")
        }
    } 
    "Mux_3_1_b" should "pass" in{
        test (new mux_3_1){ dut =>
            dut.mux_io.sel.poke(1.U)
            dut.mux_io.in0.poke(0.U)
            dut.mux_io.in1.poke(1.U)
            dut.mux_io.in2.poke(0.U)

            dut.clock.step()
            println("What's the output? The output is ",dut.mux_io.out.peek().litValue)
            dut.mux_io.out.expect(1.U,"the output should be 1")

            dut.mux_io.sel.poke(1.U)
            dut.mux_io.in0.poke(1.U)
            dut.mux_io.in1.poke(0.U)
            dut.mux_io.in2.poke(1.U)

            dut.clock.step()
            println("What's the output? The output is ",dut.mux_io.out.peek().litValue)
            dut.mux_io.out.expect(0.U,"the output should be 0")
        }
    } 
    "Mux_3_1_c" should "pass" in{
        test (new mux_3_1){ dut =>
            dut.mux_io.sel.poke(2.U)
            dut.mux_io.in0.poke(0.U)
            dut.mux_io.in1.poke(0.U)
            dut.mux_io.in2.poke(1.U)

            dut.clock.step()
            println("What's the output? The output is ",dut.mux_io.out.peek().litValue)
            dut.mux_io.out.expect(1.U,"the output should be 1")

            dut.mux_io.sel.poke(2.U)
            dut.mux_io.in0.poke(1.U)
            dut.mux_io.in1.poke(1.U)
            dut.mux_io.in2.poke(0.U)

            dut.clock.step()
            println("What's the output? The output is ",dut.mux_io.out.peek().litValue)
            dut.mux_io.out.expect(0.U,"the output should be 0")
        }
    } 

}