/**
choice4
2019-03-01 19:11:56 +08:00
Ϊ���������ܣ��߳������й����ڴ棬�����������ݲ���ȥ�����ȡ�����Կ�һЩ������������߳��޸ĺ󣬸��̵߳Ĺ����ڴ��е�ֵ�ͻ�������̲߳�һ�£�Ҳ�������ֵ��һ�£�������Ҫ�������ڴ��ֵˢ�����棬�������ˢ����������̲߳�û�п�����ʹ�� volatile �����ͨ�� cpu ָ������ǿ��Ҫ�������������д����֮�󣬲��������߳��ڶ�ȡ�ù������ʱ����Ҫ�������Լ��Ĺ����ڴ�ĸ�ֵ��ת�����´������ȡ��volatile ��֤һ����ˢ�£����ǲ�дҲ��һ�������߳̿�������
����������˵���ɺϣ������ֲ�һ��ÿ�ζ�������ȷ�ı��ϣ�
https://www.v2ex.com/amp/t/539969/2
**/
public class ThreadTest1 {
	
    private int flag = 0;
    public void first(Runnable printFirst) throws InterruptedException {
            //���flag��Ϊ0����first�̵߳ȴ���whileѭ������first�߳��������ס������һֱ��while������У���ֹ������;���룬ִ������Ĵ��룬�����߳�whileѭ��ͬ��
            while( flag != 0){
            }
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            //�����Ա����Ϊ 1
            flag = 1;
            //�����������е��߳�
    }
    public void second(Runnable printSecond) throws InterruptedException {
            //�����Ա������Ϊ1���ö��ŵȴ�
            while (flag != 1){
            }
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            //�����Ա����Ϊ 1 �������first�̸߳�ִ���꣬����ִ��second�����Ҹı��Ա����Ϊ 2
            flag = 2;
            //�����������е��߳�
    }
    public void third(Runnable printThird) throws InterruptedException {
            //���flag������2 ��һֱ���ڵȴ���״̬
            while (flag != 2){
            }
            // printThird.run() outputs "third". Do not change or remove this line.
            //�����Ա����Ϊ 2 �������second�̸߳�ִ���꣬����ִ��third�����Ҹı��Ա����Ϊ 0
            printThird.run();
            flag = 0;
    }

    public static void main(String[] args) {
        ThreadTest1 threadTest = new ThreadTest1();
        Thread thread1 = new Thread(()->{
            try {
				for(int i = 1; i < 100; i++){
					
					threadTest.first(()->{
						System.out.println("one");
					});
				}
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(()->{
            try {
				for(int i = 1; i < 100; i++){
					threadTest.second(()->{
						System.out.println("two");
					});
				}
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread3 = new Thread(()->{
            try {
				for(int i = 1; i < 100; i++){
					threadTest.third(()->{
						System.out.println("three");
					});
				}
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread3.start();
        thread1.start();
        thread2.start();
    }
}