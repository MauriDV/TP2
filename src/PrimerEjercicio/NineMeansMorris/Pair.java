public class Pair<T,E>{
	private T fst;
	private E snd;
	
	public Pair(){

	}

	public Pair(T first,E second){
		fst = first;
		snd = second;
	}

	public T getFst(){
		return fst ;
	}

	public E getSnd(){
		return snd;
	}

	public void setFst(T first){
		fst = first;
	}

	public void setSnd(E second){
		snd = second;
	}
	public void setTwo(T first, E second){
		fst=first;
		snd=second;
	}
	public String toString(){
		return "("+fst+","+snd+")";
	}
}