import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec
import FIFOB._

class FullTests extends AnyFlatSpec with ChiselScalatestTester {

    "FIFORegister" should "initialize correctly" in {
        test(new FIFORegister(1)) { dut =>
            // 检查初始化状态
            dut.io.enq.full.expect(false.B)
            dut.io.deq.empty.expect(true.B)
            dut.io.deq.dout.expect(0.U)
        }
    }

    it should "perform single write correctly" in {
        test(new FIFORegister(1)) { dut =>
            // 写操作
            dut.io.enq.write.poke(true.B)
            dut.io.enq.din.poke(1.U)
            dut.io.deq.read.poke(false.B)
            dut.clock.step()

            // 检查状态和数据
            dut.io.enq.full.expect(true.B)
            dut.io.deq.empty.expect(false.B)
            dut.io.deq.dout.expect(1.U)
        }
    }

    it should "perform single read correctly" in {
        test(new FIFORegister(1)) { dut =>
            // 预先写入数据
            dut.io.enq.write.poke(true.B)
            dut.io.enq.din.poke(1.U)
            dut.io.deq.read.poke(false.B)
            dut.clock.step()

            // 读操作
            dut.io.enq.write.poke(false.B)
            dut.io.deq.read.poke(true.B)
            dut.clock.step()

            // 检查状态和数据
            dut.io.enq.full.expect(false.B)
            dut.io.deq.empty.expect(true.B)
            dut.io.deq.dout.expect(1.U)
        }
    }

    it should "reject second write when full" in {
        test(new FIFORegister(1)) { dut =>
            // 第一次写操作
            dut.io.enq.write.poke(true.B)
            dut.io.enq.din.poke(1.U)
            dut.io.deq.read.poke(false.B)
            dut.clock.step()

            // 第二次写操作
            dut.io.enq.din.poke(0.U)
            dut.clock.step()

            // 检查状态和数据
            dut.io.enq.full.expect(true.B)
            dut.io.deq.empty.expect(false.B)
            dut.io.deq.dout.expect(1.U)
        }
    }

    it should "reject read when empty" in {
        test(new FIFORegister(1)) { dut =>
            // 初始状态下尝试读取
            dut.io.deq.read.poke(true.B)
            dut.clock.step()

            // 检查状态和数据
            dut.io.enq.full.expect(false.B)
            dut.io.deq.empty.expect(true.B)
            dut.io.deq.dout.expect(0.U)
        }
    }

    it should "alternate write and read correctly" in {
        test(new FIFORegister(1)) { dut =>
            // 写操作
            dut.io.enq.write.poke(true.B)
            dut.io.enq.din.poke(1.U)
            dut.io.deq.read.poke(false.B)
            dut.clock.step()

            // 读操作
            dut.io.enq.write.poke(false.B)
            dut.io.deq.read.poke(true.B)
            dut.clock.step()

            // 检查状态和数据
            dut.io.enq.full.expect(false.B)
            dut.io.deq.empty.expect(true.B)
            dut.io.deq.dout.expect(1.U)

            // 再次写操作
            dut.io.enq.write.poke(true.B)
            dut.io.enq.din.poke(0.U)
            dut.io.deq.read.poke(false.B)
            dut.clock.step()

            // 再次读操作
            dut.io.enq.write.poke(false.B)
            dut.io.deq.read.poke(true.B)
            dut.clock.step()

            // 检查状态和数据
            dut.io.enq.full.expect(false.B)
            dut.io.deq.empty.expect(true.B)
            dut.io.deq.dout.expect(0.U)
        }
    }

    it should "maintain full state until read" in {
        test(new FIFORegister(1)) { dut =>
            // 写操作
            dut.io.enq.write.poke(true.B)
            dut.io.enq.din.poke(1.U)
            dut.io.deq.read.poke(false.B)
            dut.clock.step()

            // 检查状态和数据
            dut.io.enq.full.expect(true.B)
            dut.io.deq.empty.expect(false.B)
            dut.io.deq.dout.expect(1.U)

            // 保持满状态
            dut.clock.step()
            dut.io.enq.full.expect(true.B)
            dut.io.deq.empty.expect(false.B)
        }
    }

    it should "maintain empty state until write" in {
        test(new FIFORegister(1)) { dut =>
            // 初始状态下，检查状态和数据
            dut.io.enq.full.expect(false.B)
            dut.io.deq.empty.expect(true.B)
            dut.io.deq.dout.expect(0.U)

            // 保持空状态
            dut.clock.step()
            dut.io.enq.full.expect(false.B)
            dut.io.deq.empty.expect(true.B)
        }
    }
}

