package ImplementPkg

import chisel3._
import chiseltest._ 
import chisel3.util._
import org.scalatest.flatspec.AnyFlatSpec

class sg_test extends AnyFlatSpec with ChiselScalatestTester{
    "sg_prcss" should "Pass" in {
        test(new sign_prcss){ dut =>
            dut.sg_io.x_sg.poke(1.U)
            dut.sg_io.y_sg.poke(1.U)

            dut.clock.step()

            println("What is the final Sign? ",dut.sg_io.output_sg.peek().litValue)
            dut.sg_io.output_sg.expect(0.U,"The final should be 0")

            dut.sg_io.x_sg.poke(0.U)
            dut.sg_io.y_sg.poke(0.U)

            dut.clock.step()

            println("What is the final Sign? ",dut.sg_io.output_sg.peek().litValue)
            dut.sg_io.output_sg.expect(0.U,"The final should be 0")

            dut.sg_io.x_sg.poke(0.U)
            dut.sg_io.y_sg.poke(1.U)

            dut.clock.step()

            println("What is the final Sign? ",dut.sg_io.output_sg.peek().litValue)
            dut.sg_io.output_sg.expect(1.U,"The final should be 1")
        }
    }
}

