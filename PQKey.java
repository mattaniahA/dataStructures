
public class PQKey<K extends Comparable<K>> implements Comparable<PQKey<K>> {

	K level;
	int keyOrder;
	static int keyCount;
	
	PQKey(K k){
		level = k;
		keyOrder = keyCount;
		keyCount++;
	}
	
	public int compareTo(PQKey<K> k){
		int temp =this.level.compareTo(k.level);
		return (temp==0) ? k.keyOrder-this.keyOrder : temp;
	}
	
	public String toString(){
		return level.toString() + "[" + keyOrder + "]";
	}

}
