import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec
import FIFOB._

class simpletest extends AnyFlatSpec with ChiselScalatestTester{
    "FIFORegister_t1" should "pass" in {
        test (new FIFORegister(1)){ dut=>
            dut.io.enq.write.poke(1.U)
            dut.io.enq.din.poke(1.U)
            dut.io.deq.read.poke(0.U)
            dut.clock.step()                // Let the clock move forward one cycle
            println("The buffer is full? " + dut.io.enq.full.peek().litValue)
            dut.io.enq.full.expect(1.U,"FIFO should be full after write")

            println("The buffer is empty? " + dut.io.deq.empty.peek().litValue)
            dut.io.deq.empty.expect(0.U,"FIFO shouldn't be empty after write")

            println("What is the output?" + dut.io.deq.dout.peek().litValue)
            dut.io.deq.dout.expect(1.U,"The output should be 0")

        }
    }
    "FIFORegister_t2" should "pass" in {
        test (new FIFORegister(1)){ dut=>
            dut.io.enq.write.poke(1.U)
            dut.io.enq.din.poke(1.U)
            dut.io.deq.read.poke(0.U)
            dut.clock.step()                // Let the clock move forward one cycle
            println("The buffer is full? " + dut.io.enq.full.peek().litValue)
            dut.io.enq.full.expect(1.U,"FIFO should be full after write")

            println("The buffer is empty? " + dut.io.deq.empty.peek().litValue)
            dut.io.deq.empty.expect(0.U,"FIFO shouldn't be empty after write")

            println("What is the output?" + dut.io.deq.dout.peek().litValue)
            dut.io.deq.dout.expect(1.U,"The output should be 0")


            dut.io.enq.write.poke(0.U)
            dut.io.deq.read.poke(1.U)
            dut.clock.step()                // Let the clock move forward one cycle
            println("The buffer is full? " + dut.io.enq.full.peek().litValue)
            dut.io.enq.full.expect(0.U,"FIFO shouldn't be full after read")

            println("The buffer is empty? " + dut.io.deq.empty.peek().litValue)
            dut.io.deq.empty.expect(1.U,"FIFO should be empty after read")

            println("What is the output?" + dut.io.deq.dout.peek().litValue)
            dut.io.deq.dout.expect(1.U,"The output should be 1")
        }
    }

}