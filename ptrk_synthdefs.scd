(
~soundfile_path = "C:\\Users\\zeffi\\Downloads\\samples\\chr_sam_006.wav";
~buff = Buffer.read(s, ~soundfile_path);

SynthDef.new(\Samplr, { |freq=440, amp=0.3, pan=0.0, buff|
    var sig;
    sig = EnvGen.kr(Env.adsr, doneAction:2) * PlayBuf.ar(2, ~buff, BufRateScale.kr(~buff) * (freq/440));
    sig = Pan2.ar(sig, pan, amp);
    Out.ar(0, sig);
}).add;

)

(
Synth.new(\Samplr, [freq: 243]);
)