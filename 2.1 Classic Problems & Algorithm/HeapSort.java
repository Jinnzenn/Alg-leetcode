import java.util.Random;
public class HeapSort{
	public void sort(int[] nums){
		for(int i = nums.length-1; i >= 0; i--){
			buildHeap(nums, i);//���ѣ��������ѣ����ڵ�Ϊֵ���ڵ�
			swap(nums, 0, i);//�����ڵ���õ�����ĩβ��i�𽥼�С��end�������������
		}
	}
	
	private void buildHeap(int[] nums, int end){
		int len = end + 1;
		//�ϸ�
		for(int i = len/2-1; i >= 0; i--){
			//����i�ڵ��Ӧ�������ӽڵ�l��r
			int left = 2*i+1, right = left+1,p = left;
			//ѡ���ӽڵ��нϴ��
			if(right < len && nums[left] < nums[right]){ p = right; }
			//ѡ�����ڵ���ӽڵ��нϴ��
			if(nums[i] < nums[p]){ swap(nums, i, p);}
		}
	}
	private void swap(int[] nums, int i, int j){
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
	
	public static void main(String[] argu){
		int[] nums = new int[1000];
		int index = 0;
		Random rand = new Random();
		while(index < nums.length){
			nums[index++] = rand.nextInt(100);
		}
		HeapSort sorter = new HeapSort();
		sorter.sort(nums);
		for(int num : nums){
			System.out.println(num);
		}
	}
}