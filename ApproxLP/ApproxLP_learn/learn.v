`timescale 1ns/ 1ps    //     与时间相关的单位 / 时间精度

module Mantissa_LP #(
    parameter BASELINE = 23
    parameter WIDTH = 23
)               // 参数化
(
    input wire[WIDTH - 1 : 0] mantissa_1,
    input wire[WIDTH - 1 : 0] mantissa_2,
    output wire[WIDTh - 1 : 0] mantissa_out,
    output wire[1:0] shift
);

    wire[WIDTH : 0] sum, diff, diff_yx;  // diff_yx 是反相的差
    assign sum = {1'b0, mantissa_1} + {1'b0, mantissa_2} // {1'b0 , mantissa} 使用来扩展尾数位宽的
    assign diff = {1'b0, mantissa_1} - {1'b0, mantissa_2}
    assign diff_yx = {1'b0, mantissa_2} - {1'b0, mantissa_1}

    //level 0
    wire x_bt_y = ~diff[WIDTH]  //检查最高位看看xy谁更大 1是y大
    
    //level 1
    wire xpy_bt_1 = sum[WIDTH]  //x+y 是否大于1     判断是否要进位

    //level 2
    

endmodule

