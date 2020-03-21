import java.math.BigInteger;

import org.bitcoinj.core.Base58;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.params.MainNetParams;

public class BitcoinAddress {

	public static void main(String[] args) throws Exception {

		BigInteger eprime = new BigInteger("957496696762772407663");
		System.out.println("eprime hex " + eprime.toString());

		BigInteger satoshiNakamoto = new BigInteger(reverseHex(bytesToHex(Base58.decode("SatoshiNakamoto"))), 16);
		System.out.println("SatoshiNakamoto  " + satoshiNakamoto);

		BigInteger phemex = new BigInteger(reverseHex(bytesToHex(Base58Xrp.decode("Phemex"))), 16);
		System.out.println("Phemex  " + phemex);

		BigInteger privateKey = eprime.multiply(satoshiNakamoto).multiply(phemex);
		System.out.println("privateKey  " + privateKey);
		System.out.println("privateKeyHex  " + privateKey.toString(16));
		
		ECKey ac = ECKey.fromPrivate(privateKey, true);
		System.out.println(ac.toAddress(MainNetParams.get()));

	}

	public static String reverseHex(String originalHex) {
		// TODO: Validation that the length is even
		int lengthInBytes = originalHex.length() / 2;
		char[] chars = new char[lengthInBytes * 2];
		for (int index = 0; index < lengthInBytes; index++) {
			int reversedIndex = lengthInBytes - 1 - index;
			chars[reversedIndex * 2] = originalHex.charAt(index * 2);
			chars[reversedIndex * 2 + 1] = originalHex.charAt(index * 2 + 1);
		}
		return new String(chars);
	}

	private static String bytesToHex(byte[] hashInBytes) {

		StringBuilder sb = new StringBuilder();
		for (byte b : hashInBytes) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();

	}

}
