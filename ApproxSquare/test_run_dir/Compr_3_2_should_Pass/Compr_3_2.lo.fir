circuit Compr_3_2 :
  module Compr_3_2 :
    input clock : Clock
    input reset : UInt<1>
    input Compr_io_a0 : UInt<1>
    input Compr_io_a1 : UInt<1>
    input Compr_io_a2 : UInt<1>
    output Compr_io_s0 : UInt<1>
    output Compr_io_c1 : UInt<1>

    node _Compr_io_s0_T = xor(Compr_io_a0, Compr_io_a1) @[Compr_3_2.scala 17:32]
    node _Compr_io_s0_T_1 = xor(_Compr_io_s0_T, Compr_io_a2) @[Compr_3_2.scala 17:46]
    node _Compr_io_c1_T = and(Compr_io_a0, Compr_io_a1) @[Compr_3_2.scala 18:33]
    node _Compr_io_c1_T_1 = and(Compr_io_a1, Compr_io_a2) @[Compr_3_2.scala 18:63]
    node _Compr_io_c1_T_2 = or(_Compr_io_c1_T, _Compr_io_c1_T_1) @[Compr_3_2.scala 18:48]
    node _Compr_io_c1_T_3 = and(Compr_io_a0, Compr_io_a2) @[Compr_3_2.scala 18:93]
    node _Compr_io_c1_T_4 = or(_Compr_io_c1_T_2, _Compr_io_c1_T_3) @[Compr_3_2.scala 18:78]
    Compr_io_s0 <= _Compr_io_s0_T_1 @[Compr_3_2.scala 17:17]
    Compr_io_c1 <= _Compr_io_c1_T_4 @[Compr_3_2.scala 18:17]
