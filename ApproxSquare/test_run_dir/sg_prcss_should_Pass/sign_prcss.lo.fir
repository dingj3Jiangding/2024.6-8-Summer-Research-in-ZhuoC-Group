circuit sign_prcss :
  module sign_prcss :
    input clock : Clock
    input reset : UInt<1>
    input sg_io_x_sg : UInt<1>
    input sg_io_y_sg : UInt<1>
    output sg_io_output_sg : UInt<1>

    node _sg_io_output_sg_T = xor(sg_io_x_sg, sg_io_y_sg) @[sign_prcss.scala 16:35]
    sg_io_output_sg <= _sg_io_output_sg_T @[sign_prcss.scala 16:21]
