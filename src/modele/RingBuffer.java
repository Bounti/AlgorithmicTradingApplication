package modele;

import org.apache.commons.collections4.queue.CircularFifoQueue;




public class RingBuffer {
	private CircularFifoQueue<Integer> fast ;
	private CircularFifoQueue<Integer> slow;
	
	public RingBuffer() {
		this.fast = new CircularFifoQueue<>(5);
		this.slow =  new CircularFifoQueue<>(20);
	}
	public CircularFifoQueue<Integer> getFast() {
		return fast;
	}
	public void setFast(CircularFifoQueue<Integer> fast) {
		this.fast = fast;
	}
	public CircularFifoQueue<Integer> getSlow() {
		return slow;
	}
	public void setSlow(CircularFifoQueue<Integer> slow) {
		this.slow = slow;
	}

}
