package phonetic_transcriber_tests;

import static org.junit.Assert.*;

import org.junit.Test;

import phonetic_transcriber.PhoneticTranscriber;

public class PhoneticTranscriberTests {
	
	private PhoneticTranscriber _phoneticTranscriber;



	
	@Test
	public void testTranscribe() {
		this._phoneticTranscriber=new PhoneticTranscriber("_", null);
		String word="apli";
		String result="a_p_l_ix";
		assertEquals(result, this._phoneticTranscriber.transcribe(word));
	}
	

	@Test
	public void testExceptionTranscribe() {
		this._phoneticTranscriber=new PhoneticTranscriber("_", null);
		String word="apģērbti";
		String result="a_p_G_EE_r_p_t_ix";
		assertEquals(result, this._phoneticTranscriber.transcribe(word));
	}
	
	@Test
	public void ēķī() {
		this._phoneticTranscriber=new PhoneticTranscriber("_", null);
		String word="ēķī";
		String result="ee_K_ii";
		assertEquals(result, this._phoneticTranscriber.transcribe(word));
	}
	
	@Test
	public void ē() {
		this._phoneticTranscriber=new PhoneticTranscriber("_", null);
		String word="ē";
		String result="EE";
		assertEquals(result, this._phoneticTranscriber.transcribe(word));
	}
	
	@Test
	public void puškins() {
		this._phoneticTranscriber=new PhoneticTranscriber("_", null);
		String word="puškins";
		String result="p_u_S_k_i_n_s";
		assertEquals(result, this._phoneticTranscriber.transcribe(word));
	}

}
