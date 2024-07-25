package ImplementPkg

import chisel3._ 
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec
import chisel3.util._

class exp_prcssT extends AnyFlatSpec with ChiselScalatestTester{
    "exp_prcss_normal_1" should "pass" in{
        test (new exp_prcss) { dut =>
            println("Test case 0: Normal number -> Normal Output")
            dut.exp_io.x_exp.poke("b111_1111".U)
            dut.exp_io.y_exp.poke("b111_1111".U)
            dut.exp_io.x_man.poke("h400000".U)
            dut.exp_io.y_man.poke("h200000".U)

            dut.clock.step()
            println("What branch? ",dut.exp_io.branch_number.peek().litValue)
            println("What is the output exp? ",dut.exp_io.out_exp.peek().litValue)
            println("What is the output man? ",dut.exp_io.out_man.peek().litValue)
            println("Is man changed? ",dut.exp_io.isman.peek())
            println("-------------")
            dut.exp_io.out_exp.expect("b111_1111".U,"The output should be 0[127]")

        }
    }
    "exp_prcss_normal_2" should "pass" in{
        test (new exp_prcss) { dut =>
            dut.exp_io.x_exp.poke("b1001_0110".U)
            dut.exp_io.y_exp.poke("b1001_0110".U)

            dut.clock.step()
            println("What is the output exp? ",dut.exp_io.out_exp.peek().litValue)
            println("-------------")
            dut.exp_io.out_exp.expect("b1010_1101".U)

        }
    }

    "exp_prcss_special" should "pass" in {
        test (new exp_prcss) { dut =>  
            //Test case 1: 0 & normal
            println("Test case 1: 0 & normal")
            dut.exp_io.x_exp.poke("h00".U)
            dut.exp_io.y_exp.poke("h81".U)
            dut.exp_io.x_man.poke("h000000".U)
            dut.exp_io.y_man.poke("h200000".U)

            dut.clock.step()
            println("What is the output exp? ",dut.exp_io.out_exp.peek().litValue)
            println("What is the output mantissa? ",dut.exp_io.out_man.peek().litValue)
            println("-------------")
            dut.exp_io.out_exp.expect("b0".U)
            dut.exp_io.out_man.expect("b0".U)

        }

        test (new exp_prcss) { dut =>                   
            //Test case 2: Inf & normal
            println("Test Case2: Inf & normal")
            dut.exp_io.x_exp.poke("hFF".U)
            dut.exp_io.y_exp.poke("h81".U)
            dut.exp_io.x_man.poke("h000000".U)
            dut.exp_io.y_man.poke("h200000".U)

            dut.clock.step()
            println("What is the output exp? ",dut.exp_io.out_exp.peek().litValue)
            println("What is the output mantissa? ",dut.exp_io.out_man.peek().litValue)
            println("Is mantissa changed? ",dut.exp_io.isman.peek())
            println("-------------")
            dut.exp_io.out_exp.expect("hFF".U)
            dut.exp_io.out_man.expect("b0".U)
        }

        test(new exp_prcss) { dut =>
            // Test case 3: NaN & normal
            println("Test case 3: NaN & normal")
            dut.exp_io.x_exp.poke("hFF".U)
            dut.exp_io.y_exp.poke("h81".U)
            dut.exp_io.x_man.poke("h200000".U)
            dut.exp_io.y_man.poke("h200000".U)

            dut.clock.step()
            println("What is the output exp? ",dut.exp_io.out_exp.peek().litValue)
            println("What is the output mantissa? ",dut.exp_io.out_man.peek().litValue)
            println("Is mantissa changed? ",dut.exp_io.isman.peek())
            println("-------------")
            dut.exp_io.out_exp.expect("hFF".U)
            dut.exp_io.out_man.expect("b0".U)
        }

        test(new exp_prcss) { dut =>
            // Test case 4: Both Inf
            println("Test case 4: Both Inf")
            dut.exp_io.x_exp.poke("hFF".U)
            dut.exp_io.y_exp.poke("hFF".U)
            dut.exp_io.x_man.poke("h000000".U)
            dut.exp_io.y_man.poke("h000000".U)

            dut.clock.step()
            println("What is the output exp? ",dut.exp_io.out_exp.peek().litValue)
            println("What is the output mantissa? ",dut.exp_io.out_man.peek().litValue)
            println("Is mantissa changed? ",dut.exp_io.isman.peek())
            println("-------------")
            dut.exp_io.out_exp.expect("hFF".U)
            dut.exp_io.out_man.expect("b0".U)
        }

        test(new exp_prcss) { dut =>
            // Test case 5: Denormal and Normal
            println("Test case 5: Denormal and Normal")
            dut.exp_io.x_exp.poke("h00".U)
            dut.exp_io.y_exp.poke("h81".U)
            dut.exp_io.x_man.poke("h000001".U)
            dut.exp_io.y_man.poke("h200000".U)

            dut.clock.step()
            println("What is the output exp? ",dut.exp_io.out_exp.peek().litValue)
            println("What is the output mantissa? ",dut.exp_io.out_man.peek().litValue)
            println("Is mantissa changed? ",dut.exp_io.isman.peek())
            println("-------------")
            dut.exp_io.out_exp.expect("h3".U)
            dut.exp_io.out_man.expect("b0".U)
        }

        test(new exp_prcss) { dut =>
            // Test case 6: zero & Inf
            println("Test case 6: zero & Inf")
            dut.exp_io.x_exp.poke("h00".U)
            dut.exp_io.y_exp.poke("hFF".U)
            dut.exp_io.x_man.poke("h000000".U)
            dut.exp_io.y_man.poke("h000000".U)

            dut.clock.step()
            println("What is the output exp? ",dut.exp_io.out_exp.peek().litValue)
            println("What is the output mantissa? ",dut.exp_io.out_man.peek().litValue)
            println("Is mantissa changed? ",dut.exp_io.isman.peek())
            println("-------------")
            dut.exp_io.out_exp.expect("hFF".U)
            dut.exp_io.out_man.expect("h0".U)
        }

        test(new exp_prcss) { dut =>
            // Test case 7: zero & Inf
            println("Test case 7: zero & Nan")
            dut.exp_io.x_exp.poke("h00".U)
            dut.exp_io.y_exp.poke("hFF".U)
            dut.exp_io.x_man.poke("h000000".U)
            dut.exp_io.y_man.poke("h000000".U)

            dut.clock.step()
            println("What is the output exp? ",dut.exp_io.out_exp.peek().litValue)
            println("What is the output mantissa? ",dut.exp_io.out_man.peek().litValue)
            println("Is mantissa changed? ",dut.exp_io.isman.peek())
            println("-------------")
            dut.exp_io.out_exp.expect("hFF".U)
        }

        test(new exp_prcss) { dut =>
            // Test case 9: Both Denormal 
            println("Test case 9: Both Denormal ")
            dut.exp_io.x_exp.poke("h00".U)
            dut.exp_io.y_exp.poke("h00".U)
            dut.exp_io.x_man.poke("h000001".U)
            dut.exp_io.y_man.poke("h000001".U)

            dut.clock.step()
            println("What is the output exp? ",dut.exp_io.out_exp.peek().litValue) 
            println("What is the output mantissa? ",dut.exp_io.out_man.peek().litValue)
            println("Is mantissa changed? ",dut.exp_io.isman.peek())
            println("-------------")
            dut.exp_io.out_exp.expect("h0".U)
            dut.exp_io.out_man.expect("h0".U)
        }

        test(new exp_prcss) { dut =>
            // Test case 10: 2 Normal Number lead to overflow 
            println("Test case 10: 2 Normal Number lead to overflow")
            dut.exp_io.x_exp.poke("hFE".U)
            dut.exp_io.y_exp.poke("hFE".U)
            dut.exp_io.x_man.poke("h400000".U)
            dut.exp_io.y_man.poke("h400000".U)

            dut.clock.step()
            println("What branch? ",dut.exp_io.branch_number.peek().litValue)
            println("What is the output exp? ",dut.exp_io.out_exp.peek().litValue) 
            println("What is the output mantissa? ",dut.exp_io.out_man.peek().litValue)
            println("Is mantissa changed? ",dut.exp_io.isman.peek())
            println("-------------")
            // dut.exp_io.out_exp.expect("hFF".U)
            // dut.exp_io.out_man.expect("h0".U)
        }

        test(new exp_prcss) { dut =>
            // Test case 11: 2 Normal Number lead to underflow
            println("Test case 11: 2 Normal Number lead to underflow")
            dut.exp_io.x_exp.poke("h01".U)
            dut.exp_io.y_exp.poke("h01".U)
            dut.exp_io.x_man.poke("h400000".U)
            dut.exp_io.y_man.poke("h400000".U)

            dut.clock.step()
            println("What branch? ",dut.exp_io.branch_number.peek().litValue)
            println("What is the output exp? ",dut.exp_io.out_exp.peek().litValue) 
            println("What is the output mantissa? ",dut.exp_io.out_man.peek().litValue)
            println("Is mantissa changed? ",dut.exp_io.isman.peek())
            println("Is the result a Denormal? ",dut.exp_io.isDenormal.peek())
            println("-------------")
            dut.exp_io.out_exp.expect("h00".U)
            dut.exp_io.isDenormal.expect(true.B)
        }

        
    }
}

