package com.caiw.nettydemo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerHandler  extends SimpleChannelInboundHandler{


    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf=(ByteBuf)msg;
        byte [] data=new byte[buf.readableBytes()];
        buf.readBytes(data);
        String info = new String(data);
        System.out.println(info);
        //返回客户端消息
        String returnInfo = "消息：“"+info+"”发送成功！";
        ByteBuf sendMsg= Unpooled.copiedBuffer(returnInfo.getBytes());
        ctx.writeAndFlush(sendMsg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

}
