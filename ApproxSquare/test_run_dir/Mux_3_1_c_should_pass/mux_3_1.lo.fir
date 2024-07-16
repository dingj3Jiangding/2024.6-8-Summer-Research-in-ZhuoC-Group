circuit mux_3_1 :
  module mux_3_1 :
    input clock : Clock
    input reset : UInt<1>
    input mux_io_sel : UInt<2>
    input mux_io_in0 : UInt<1>
    input mux_io_in1 : UInt<1>
    input mux_io_in2 : UInt<1>
    output mux_io_out : UInt<1>

    node _mux_io_out_T = eq(mux_io_sel, UInt<1>("h0")) @[mux_3_1.scala 16:35]
    node _mux_io_out_T_1 = eq(mux_io_sel, UInt<1>("h1")) @[mux_3_1.scala 17:39]
    node _mux_io_out_T_2 = mux(_mux_io_out_T_1, mux_io_in1, mux_io_in2) @[mux_3_1.scala 17:27]
    node _mux_io_out_T_3 = mux(_mux_io_out_T, mux_io_in0, _mux_io_out_T_2) @[mux_3_1.scala 16:23]
    mux_io_out <= _mux_io_out_T_3 @[mux_3_1.scala 16:16]
