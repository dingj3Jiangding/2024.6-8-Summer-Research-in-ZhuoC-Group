package ImplementPkg

import chisel3._ 
import chiseltest._ 
import chisel3.util._ 
import org.scalatest.flatspec.AnyFlatSpec

class comp_test extends AnyFlatSpec with ChiselScalatestTester{
    "Compr_3_2_a" should "Pass" in {
        test(new Compr_3_2) { dut =>
            dut.Compr_io.a0.poke(0.U)
            dut.Compr_io.a1.poke(0.U)
            dut.Compr_io.a2.poke(0.U)

            dut.clock.step()
            println("What is the s0? ",dut.Compr_io.s0.peek().litValue)
            dut.Compr_io.s0.expect(0.U,"The s0 should be 0")
            
            println("What is the c1? ",dut.Compr_io.c1.peek().litValue)
            dut.Compr_io.c1.expect(0.U,"The c1 should be 0")

        }
        test(new Compr_3_2) { dut =>
            dut.Compr_io.a0.poke(1.U)
            dut.Compr_io.a1.poke(0.U)
            dut.Compr_io.a2.poke(0.U)

            dut.clock.step()
            println("What is the s0? ",dut.Compr_io.s0.peek().litValue)
            dut.Compr_io.s0.expect(1.U,"The s0 should be 0")
            
            println("What is the c1? ",dut.Compr_io.c1.peek().litValue)
            dut.Compr_io.c1.expect(0.U,"The c1 should be 0")

        }
        test(new Compr_3_2) { dut =>
            dut.Compr_io.a0.poke(0.U)
            dut.Compr_io.a1.poke(1.U)
            dut.Compr_io.a2.poke(0.U)

            dut.clock.step()
            println("What is the s0? ",dut.Compr_io.s0.peek().litValue)
            dut.Compr_io.s0.expect(1.U,"The s0 should be 0")
            
            println("What is the c1? ",dut.Compr_io.c1.peek().litValue)
            dut.Compr_io.c1.expect(0.U,"The c1 should be 0")

        }

        test(new Compr_3_2) { dut =>
            dut.Compr_io.a0.poke(0.U)
            dut.Compr_io.a1.poke(0.U)
            dut.Compr_io.a2.poke(1.U)

            dut.clock.step()
            println("What is the s0? ",dut.Compr_io.s0.peek().litValue)
            dut.Compr_io.s0.expect(1.U,"The s0 should be 0")
            
            println("What is the c1? ",dut.Compr_io.c1.peek().litValue)
            dut.Compr_io.c1.expect(0.U,"The c1 should be 0")

        }
    }

    "Compr_3_2_b" should "Pass" in {
        test(new Compr_3_2) { dut =>
            dut.Compr_io.a0.poke(1.U)
            dut.Compr_io.a1.poke(1.U)
            dut.Compr_io.a2.poke(0.U)

            dut.clock.step()
            println("What is the s0? ",dut.Compr_io.s0.peek().litValue)
            dut.Compr_io.s0.expect(0.U,"The s0 should be 0")
            
            println("What is the c1? ",dut.Compr_io.c1.peek().litValue)
            dut.Compr_io.c1.expect(1.U,"The c1 should be 0")

        }
        test(new Compr_3_2) { dut =>
            dut.Compr_io.a0.poke(1.U)
            dut.Compr_io.a1.poke(0.U)
            dut.Compr_io.a2.poke(1.U)

            dut.clock.step()
            println("What is the s0? ",dut.Compr_io.s0.peek().litValue)
            dut.Compr_io.s0.expect(0.U,"The s0 should be 0")
            
            println("What is the c1? ",dut.Compr_io.c1.peek().litValue)
            dut.Compr_io.c1.expect(1.U,"The c1 should be 0")

        }
        test(new Compr_3_2) { dut =>
            dut.Compr_io.a0.poke(0.U)
            dut.Compr_io.a1.poke(1.U)
            dut.Compr_io.a2.poke(1.U)

            dut.clock.step()
            println("What is the s0? ",dut.Compr_io.s0.peek().litValue)
            dut.Compr_io.s0.expect(0.U,"The s0 should be 0")
            
            println("What is the c1? ",dut.Compr_io.c1.peek().litValue)
            dut.Compr_io.c1.expect(1.U,"The c1 should be 0")

        }

        test(new Compr_3_2) { dut =>
            dut.Compr_io.a0.poke(1.U)
            dut.Compr_io.a1.poke(1.U)
            dut.Compr_io.a2.poke(1.U)

            dut.clock.step()
            println("What is the s0? ",dut.Compr_io.s0.peek().litValue)
            dut.Compr_io.s0.expect(1.U,"The s0 should be 0")
            
            println("What is the c1? ",dut.Compr_io.c1.peek().litValue)
            dut.Compr_io.c1.expect(1.U,"The c1 should be 0")

        }
    }  
}