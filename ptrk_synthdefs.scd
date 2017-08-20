~soundfile_path = "C:\\Users\\zeffi\\Downloads\\samples\\chr_sam_006.wav";
~buff = Buffer.read(s, ~soundfile_path);

~sf_path2 = "C:/Users/zeffi/Downloads/samples/nice_bd_singleBD.wav";
~buff2 = Buffer.read(s, ~sf_path2);


/// sample 1
(

SynthDef.new(\Samplr, { |freq=440, amp=0.3, pan=0.0, offset=0, dur, atk, rel|
    var sig;
    sig = EnvGen.kr(Env.adsr, doneAction:2) * PlayBuf.ar(2, ~buff, BufRateScale.kr(~buff) * (freq/440), 1.0, offset, doneAction:2);
    sig = Pan2.ar(sig, pan, amp);
    Out.ar(0, sig);
}).add;

)

// (
// Synth.new(\Samplr, [freq: 243, offset:5600]);
// )


(
Pdef(
    \Samplepat,
    Pbind(
        \instrument, \Samplr,
        \dur, Pwhite(0.5, 0.5, inf),
        \midinote, 56,
        \offset, Pwhite(630, 28310, inf),
        \amp, Pkey(\harmonic).reciprocal * 0.4,
        \pan, Pwhite(-0.2, 0.2, inf)
    );
).play;
)

//////////// sample 2

(

SynthDef.new(\Samplr2, { |freq=440, amp=0.3, pan=0.0, offset=0, dur, atk, rel|
    var sig;
    sig = EnvGen.kr(Env.adsr, doneAction:2) * PlayBuf.ar(2, ~buff2, BufRateScale.kr(~buff2) * (freq/440), 1.0, offset, doneAction:2);
    sig = Pan2.ar(sig, pan, amp);
    Out.ar(0, sig);
}).add;

)


(
Pdef(
    \Samplepat2,
    Pbind(
        \instrument, \Samplr2,
        \dur, Pwhite(0.5, 0.5, inf),
        \midinote, 69,
        \amp, Pkey(\harmonic).reciprocal * 0.4,
        \pan, Pwhite(-0.2, 0.2, inf)
    );
).play;
)
