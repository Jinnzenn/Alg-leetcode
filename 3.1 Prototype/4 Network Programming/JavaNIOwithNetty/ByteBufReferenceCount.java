import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.util.ReferenceCountUtil;

public class ByteBufReferenceCount {
  public static void main(String[] args) {
    //ͨ���ڴ�ط���һ��ʹ�����ü��������������ڵ�ByteBuf
    ByteBuf buffer = PooledByteBufAllocator.DEFAULT.buffer();
    //��ʼ�����ü���Ϊ1
    System.out.println(buffer.refCnt());
    //ģ��bufferʹ��
    buffer.writeByte(1);
    buffer.readByte();
    //����
    buffer.release();
    //Ҳ����ʹ��ReferenceCountUtil���������
    //ReferenceCountUtil.release(buffer);

    //�����ü���Ϊ0�����󱻻���
    System.out.println(buffer.refCnt());
    //�����ü���Ϊ0ʱ�����󲻿��ã������κζ�д���������׳��쳣
    buffer.writeByte(1);
  }
}