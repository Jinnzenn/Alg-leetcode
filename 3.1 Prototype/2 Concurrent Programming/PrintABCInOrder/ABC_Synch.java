//����ѧ�ҽ�����������������
//�����߳�ͬʱ��ȡ��ǰһ��prev��Ȼ���ȡ�Լ�self
public class ABC_SynchDeadLock {
    public static class ThreadPrinter implements Runnable {
        private String name;
        private Object prev;
        private Object self;

        private ThreadPrinter(String name, Object prev, Object self) {
            this.name = name;
            this.prev = prev;
            this.self = self;
        }

        @Override
        public void run() {
            int count = 1000;
            while (count > 0) {// ���̲߳�����������if������ʹ��whileѭ��
                synchronized (prev) { // �Ȼ�ȡ prev ��
                    synchronized (self) {// �ٻ�ȡ self ��
                        System.out.print(name);// ��ӡ
                        count--;
						//���˳�self����ȻҪ�Ȼ�����������self���߳�
                        self.notifyAll();// ���������߳̾���self����ע���ʱself����δ�����ͷš�
                    }
                    // ��ʱִ����self��ͬ���飬��ʱself�����ͷš�
                    try {
                        if (count == 0) {// ���count==0,��ʾ�������һ�δ�ӡ������ͨ��notifyAll�����ͷŶ�������
                            prev.notifyAll();
                        } else {
                            prev.wait(); // �����ͷ� prev������ǰ�߳����ߣ��ȴ�����
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        ThreadPrinter pa = new ThreadPrinter("A", c, a);
        ThreadPrinter pb = new ThreadPrinter("B", a, b);
        ThreadPrinter pc = new ThreadPrinter("C", b, c);

        new Thread(pa).start();
        //Thread.sleep(10);// ��֤��ʼABC������˳�򣬲���ȥ��
        new Thread(pb).start();
        //Thread.sleep(10);
        new Thread(pc).start();
        //Thread.sleep(10);
    }
}


//��ȷ����Ӧ����ͨ��ԭ�ӱ����������߳�ͨ��