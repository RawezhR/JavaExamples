
public class DecoderRing {

	private int number;
	private int lmin = 97;
	private int lmax = 122; //a-z: 97-122
	private int umin = 65;
	private int umax = 90; //A-Z: 65-90
	
	public DecoderRing() {
		number = 13;
	}
	
	public DecoderRing(int num) {
		if (num < 1 || num > 25)
			number = 13;
	}
	
	public String encode(String s) {
		if (s == null) s = "";
		String ret = "";
		
		int b, tmp,max,min;
		for (int i=0; i < s.length(); i++) {
			b = (byte)s.charAt(i);
			
			if (b == 32) {
				//space
				ret = ret + " ";
				continue;
			}
			
			if (b <= lmax && b >= lmin) {
				max = lmax;
				min = lmin;
			} else if (b <= umax && b >= umin) {
				max = umax;
				min = umin;
			} else {
				ret = ret + " ";
				continue;
			}
			
			if ( (b + number) > max ) {
				tmp = (b + number) - max + min - 1;
			} else {
				tmp = b + number;
			}
			ret = ret + (char)tmp;
		}
		return ret;
	}

	public String decode(String s) {
		if (s == null) s = "";
		String ret = "";
		
		int b, tmp, max, min;
		for (int i=0; i < s.length(); i++) {
			b = (byte)s.charAt(i);
			
			if (b == 32) {
				//space
				ret = ret + " ";
				continue;
			}
			
			if (b <= lmax && b >= lmin) {
				max = lmax;
				min = lmin;
			} else if (b <= umax && b >= umin) {
				max = umax;
				min = umin;
			} else {
				ret = ret + " ";
				continue;
			}
			
			if ( (b - number) < min ) {
				tmp = max - (min - (b - number)) + 1;
			} else {
				tmp = b - number;
			}
			ret = ret + (char)tmp;
		}
		return ret;
	}
	
	public static void main(String[] args) {
		
		DecoderRing dr = new DecoderRing();
		
		System.out.println(dr.decode("pbqrarjovrf ner gur zbfg fhccbegvir pbzzhavgl")); //codenewbies are the most supportive community
		
		String s1 = dr.encode("no one can read this secret message");
		String s2 = dr.encode("it is so super secret");
		String s3 = dr.encode("only a CodeNewbie can figure it out");
		System.out.println(dr.decode(s1));
		System.out.println(dr.decode(s2));
		System.out.println(dr.decode(s3));
		
		dr = new DecoderRing(17); //supports ROT1 - ROT25
		s1 = dr.encode("no one can read this secret message");
		s2 = dr.encode("it is so super secret");
		s3 = dr.encode("only a CodeNewbie can figure it out");
		System.out.println(dr.decode(s1));
		System.out.println(dr.decode(s2));
		System.out.println(dr.decode(s3));
		
	}

}
