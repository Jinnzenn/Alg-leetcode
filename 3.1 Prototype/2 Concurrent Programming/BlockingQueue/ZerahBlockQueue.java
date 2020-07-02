import java.util.*;

public class ZerahBlockQueue {
    //��������
    private List<Integer> container = new ArrayList<>();
    private Lock lock = new ReentrantLock();
    //Condition
    //  ����Ϊ��
    private Condition isNull = lock.newCondition();
    // ��������
    private Condition isFull = lock.newCondition();
    private volatile int size;
    private volatile int capacity;

    ZerahBlockQueue(int cap) {
        this.capacity = cap;
    }
        
    public void add(int data) {
        try {
            lock.lock();
            try {
                while (size >= capacity) {
                    System.out.println("�����������ͷ������ȴ���������������");
                    isFull.await();
                }
            } catch (InterruptedException e) {
                isFull.signal();
                e.printStackTrace();
            }
            ++size;
            container.add(data);
            isNull.signal();
        } finally {
            lock.unlock();
        }
    }

    public int take(){
        try {
            lock.lock();
            try {
                while (size == 0){
                    System.out.println("�������п��ˣ��ͷ������ȴ���������������");
                    isNull.await();
                }
            }catch (InterruptedException e){
                isFull.signal();
                e.printStackTrace();
            }
            --size;
            int res = container.get(0);
            container.remove(0);
            isFull.signal();
            return res ;
        }finally {
            lock.unlock();
        }
    }


}
