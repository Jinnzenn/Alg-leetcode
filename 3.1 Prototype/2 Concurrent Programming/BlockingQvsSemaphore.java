import java.util.concurrent.Semaphore;

public class BlockingQvsSemaphore {

    /** 
     * @Title: ʹ��Semaphoreʵ����������
     * @Description: ʹ��ֻ��1����ɵ�
     * @param @param args    �趨�ļ� 
     * @return void    �������� 
     * @throws 
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new Thread(new Producer()).start();  
		new Thread(new Producer()).start();  
		new Thread(new Producer()).start();  
        new Thread(new BConsumer()).start();
    }

}
/**
 * �ź���
 * */
class Signs{  
    static Semaphore empty=new Semaphore(10); //�ź�������¼�ֿ�յ�λ��  
    static Semaphore full=new Semaphore(0);   //�ź�������¼�ֿ�����λ��  
    static Semaphore mutex=new Semaphore(1);  //�ٽ�����������ź���(�������ź���)���൱�ڻ����������ڱ������̰߳�ȫ��  
} 
/**
 * ������
 * */
class Producer implements Runnable{  
    @SuppressWarnings("static-access")
    public void run() {
        try {
            while (true) {
                Signs.empty.acquire(); // �ݼ��ֿ���ź����������Ѽ�������1
                Signs.mutex.acquire(); // �����ٽ���
                System.out.println("����һ����Ʒ����ֿ�");
                Signs.mutex.release(); // �뿪�ٽ���
                Signs.full.release(); // �����ֿ����ź�����������������1
                Thread.currentThread().sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
/**
 * ������
 * */
class BConsumer implements Runnable{  
    @SuppressWarnings("static-access")
    public void run() {
        try {
            while (true) {
                Signs.full.acquire(); // �ݼ��ֿ����ź�����������������1
                Signs.mutex.acquire();
                System.out.println("�Ӳֿ��ó�һ����Ʒ����");
                Signs.mutex.release();
                Signs.empty.release(); // �����ֿ���ź����������Ѽ�������1
                Thread.currentThread().sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }  
   
}