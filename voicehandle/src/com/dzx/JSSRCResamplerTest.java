package com.dzx;

import com.dzx.sound.sampled.AudioFileFormat;
import com.dzx.sound.sampled.AudioFormat;
import com.dzx.sound.sampled.AudioInputStream;
import com.dzx.sound.sampled.AudioSystem;

import java.io.File;

/**
 * JSSRCResampler Tester.
 *
 * @author Maksim Khadkevich
 * @since <pre>03/25/2011</pre>
 * @version 1.0
 */
public class JSSRCResamplerTest {

    /**
     *
     * This simple test downsamples and upsamples two test files
     *
     */
    public void testReadSamples() throws Exception {

        String[] fileNames = new String[]{"/mono_short_test.wav", "/stereo_long_test.wav"};

        float[] outSamplingRates = new float[]{11025f, 96000f};

        for (String inFileName:fileNames) {
            for (float outSamplingRate:outSamplingRates) {
                String inFilePath = this.getClass().getResource(inFileName).getPath();
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(inFilePath));
                AudioFormat sourceFormat =  audioInputStream.getFormat();


                AudioFormat targetFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                        outSamplingRate,
                        sourceFormat.getSampleSizeInBits(),
                        sourceFormat.getChannels(),
                        sourceFormat.getFrameSize(),
                        sourceFormat.getFrameRate(),
                        sourceFormat.isBigEndian());

                AudioInputStream inputStream = AudioSystem.getAudioInputStream(targetFormat, audioInputStream);

                AudioSystem.write(inputStream, AudioFileFormat.Type.WAVE, new File(String.format("%s_resampled_%d.wav", inFilePath, (int) outSamplingRate)));
            }
        }

    }



}
