circuit exp_prcss :
  module exp_prcss :
    input clock : Clock
    input reset : UInt<1>
    input exp_io_x_exp : UInt<8>
    input exp_io_y_exp : UInt<8>
    output exp_io_out_exp : UInt<8>

    node _exp_io_out_exp_T = add(exp_io_x_exp, exp_io_y_exp) @[exp_prcss.scala 15:40]
    node _exp_io_out_exp_T_1 = tail(_exp_io_out_exp_T, 1) @[exp_prcss.scala 15:40]
    node _exp_io_out_exp_T_2 = sub(_exp_io_out_exp_T_1, UInt<7>("h7f")) @[exp_prcss.scala 15:55]
    node _exp_io_out_exp_T_3 = tail(_exp_io_out_exp_T_2, 1) @[exp_prcss.scala 15:55]
    exp_io_out_exp <= _exp_io_out_exp_T_3 @[exp_prcss.scala 15:24]
