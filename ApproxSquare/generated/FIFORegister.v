module FIFORegister(
  input   clock,
  input   reset,
  input   io_enq_write,
  output  io_enq_full,
  input   io_enq_din,
  input   io_deq_read,
  output  io_deq_empty,
  output  io_deq_dout
);
`ifdef RANDOMIZE_REG_INIT
  reg [31:0] _RAND_0;
  reg [31:0] _RAND_1;
`endif // RANDOMIZE_REG_INIT
  reg  dataReg; // @[FIFOB.scala 26:26]
  reg  stateReg; // @[FIFOB.scala 27:27]
  wire  _T = ~stateReg; // @[FIFOB.scala 33:19]
  wire  _T_2 = ~reset; // @[FIFOB.scala 34:15]
  wire  _GEN_1 = io_enq_write | stateReg; // @[FIFOB.scala 35:39 37:22 27:27]
  assign io_enq_full = stateReg; // @[FIFOB.scala 50:29]
  assign io_deq_empty = ~stateReg; // @[FIFOB.scala 49:30]
  assign io_deq_dout = dataReg; // @[FIFOB.scala 48:17]
  always @(posedge clock) begin
    if (reset) begin // @[FIFOB.scala 26:26]
      dataReg <= 1'h0; // @[FIFOB.scala 26:26]
    end else if (~stateReg) begin // @[FIFOB.scala 33:31]
      if (io_enq_write) begin // @[FIFOB.scala 35:39]
        dataReg <= io_enq_din; // @[FIFOB.scala 36:21]
      end
    end
    if (reset) begin // @[FIFOB.scala 27:27]
      stateReg <= 1'h0; // @[FIFOB.scala 27:27]
    end else if (~stateReg) begin // @[FIFOB.scala 33:31]
      stateReg <= _GEN_1;
    end else if (io_deq_read) begin // @[FIFOB.scala 41:38]
      stateReg <= 1'h0; // @[FIFOB.scala 42:22]
    end
    `ifndef SYNTHESIS
    `ifdef PRINTF_COND
      if (`PRINTF_COND) begin
    `endif
        if (_T & ~reset) begin
          $fwrite(32'h80000002,"the buffer is empty\n"); // @[FIFOB.scala 34:15]
        end
    `ifdef PRINTF_COND
      end
    `endif
    `endif // SYNTHESIS
    `ifndef SYNTHESIS
    `ifdef PRINTF_COND
      if (`PRINTF_COND) begin
    `endif
        if (~_T & _T_2) begin
          $fwrite(32'h80000002,"the buffer is full\n"); // @[FIFOB.scala 40:15]
        end
    `ifdef PRINTF_COND
      end
    `endif
    `endif // SYNTHESIS
  end
// Register and memory initialization
`ifdef RANDOMIZE_GARBAGE_ASSIGN
`define RANDOMIZE
`endif
`ifdef RANDOMIZE_INVALID_ASSIGN
`define RANDOMIZE
`endif
`ifdef RANDOMIZE_REG_INIT
`define RANDOMIZE
`endif
`ifdef RANDOMIZE_MEM_INIT
`define RANDOMIZE
`endif
`ifndef RANDOM
`define RANDOM $random
`endif
`ifdef RANDOMIZE_MEM_INIT
  integer initvar;
`endif
`ifndef SYNTHESIS
`ifdef FIRRTL_BEFORE_INITIAL
`FIRRTL_BEFORE_INITIAL
`endif
initial begin
  `ifdef RANDOMIZE
    `ifdef INIT_RANDOM
      `INIT_RANDOM
    `endif
    `ifndef VERILATOR
      `ifdef RANDOMIZE_DELAY
        #`RANDOMIZE_DELAY begin end
      `else
        #0.002 begin end
      `endif
    `endif
`ifdef RANDOMIZE_REG_INIT
  _RAND_0 = {1{`RANDOM}};
  dataReg = _RAND_0[0:0];
  _RAND_1 = {1{`RANDOM}};
  stateReg = _RAND_1[0:0];
`endif // RANDOMIZE_REG_INIT
  `endif // RANDOMIZE
end // initial
`ifdef FIRRTL_AFTER_INITIAL
`FIRRTL_AFTER_INITIAL
`endif
`endif // SYNTHESIS
endmodule
