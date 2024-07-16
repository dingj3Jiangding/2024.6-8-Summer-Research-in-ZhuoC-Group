circuit FIFORegister :
  module FIFORegister :
    input clock : Clock
    input reset : UInt<1>
    output io : { enq : { flip write : UInt<1>, full : UInt<1>, flip din : UInt<1>}, deq : { flip read : UInt<1>, empty : UInt<1>, dout : UInt<1>}}

    reg dataReg : UInt<1>, clock with :
      reset => (reset, UInt<1>("h0")) @[FIFOB.scala 26:26]
    reg stateReg : UInt<1>, clock with :
      reset => (reset, UInt<1>("h0")) @[FIFOB.scala 27:27]
    io.enq.full <= UInt<1>("h0") @[FIFOB.scala 29:17]
    io.deq.empty <= UInt<1>("h1") @[FIFOB.scala 30:18]
    io.deq.dout <= UInt<1>("h0") @[FIFOB.scala 31:17]
    node _T = eq(stateReg, UInt<1>("h0")) @[FIFOB.scala 33:19]
    when _T : @[FIFOB.scala 33:31]
      node _T_1 = bits(reset, 0, 0) @[FIFOB.scala 34:15]
      node _T_2 = eq(_T_1, UInt<1>("h0")) @[FIFOB.scala 34:15]
      when _T_2 : @[FIFOB.scala 34:15]
        printf(clock, UInt<1>("h1"), "the buffer is empty\n") : printf @[FIFOB.scala 34:15]
      node _T_3 = eq(io.enq.write, UInt<1>("h1")) @[FIFOB.scala 35:28]
      when _T_3 : @[FIFOB.scala 35:39]
        dataReg <= io.enq.din @[FIFOB.scala 36:21]
        stateReg <= UInt<1>("h1") @[FIFOB.scala 37:22]
    else :
      node _T_4 = bits(reset, 0, 0) @[FIFOB.scala 40:15]
      node _T_5 = eq(_T_4, UInt<1>("h0")) @[FIFOB.scala 40:15]
      when _T_5 : @[FIFOB.scala 40:15]
        printf(clock, UInt<1>("h1"), "the buffer is full\n") : printf_1 @[FIFOB.scala 40:15]
      node _T_6 = eq(io.deq.read, UInt<1>("h1")) @[FIFOB.scala 41:27]
      when _T_6 : @[FIFOB.scala 41:38]
        stateReg <= UInt<1>("h0") @[FIFOB.scala 42:22]
    io.deq.dout <= dataReg @[FIFOB.scala 48:17]
    node _io_deq_empty_T = eq(stateReg, UInt<1>("h0")) @[FIFOB.scala 49:30]
    io.deq.empty <= _io_deq_empty_T @[FIFOB.scala 49:18]
    node _io_enq_full_T = eq(stateReg, UInt<1>("h1")) @[FIFOB.scala 50:29]
    io.enq.full <= _io_enq_full_T @[FIFOB.scala 50:17]

