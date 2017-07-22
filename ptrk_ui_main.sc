s.boot;


(
SynthDef("hatsound", {
    arg freq=440, dur=0.5;
    var outArray, mamp;
    mamp = Line.kr(0.2, 0, dur, doneAction: 2);
    outArray = [PinkNoise.ar(mul: mamp), PinkNoise.ar(mul: mamp)];
    outArray = HPF.ar(outArray, freq);
    Out.ar(0, outArray);
}).add;

)

Synth(\hatsound, [\freq, 1220, \dur, 0.1]);


// execute first

(

~num_tracks = 6;
~num_rows = 16;
~cursor_cell = [0, 0];

~cursor_highlight = {
    | keycode |

    switch (keycode,
      39, { ~cursor_cell[0] = ~cursor_cell[0] + 1 },    // right
      37, { ~cursor_cell[0] = ~cursor_cell[0] - 1 },    // left
      38, { ~cursor_cell[1] = ~cursor_cell[1] - 1 },    // up
      40, { ~cursor_cell[1] = ~cursor_cell[1] + 1 }     // down
    );
    ~cursor_cell[0] = ~cursor_cell[0] % ~num_tracks;
    ~cursor_cell[1] = ~cursor_cell[1] % ~num_rows;
    ~cursor_cell.postln;
};


GUI.qt;

Window.closeAll;

w = Window.new("ptrk", Rect.new(840, 330, 1060, 720))
.front
.alwaysOnTop_(true);

~ypos = 43;
~lineheight = 20;
~textfont = "Consolas";
~textcol = Color(0.7, 0.7, 0.7, 1.0);
~local_offsetx = 90;

w.drawFunc_{|me|


    // draw header
    ~num_tracks.do { |idx|
        var offsetx = 90;
        var header_x = 95;
        var text_ypos = 22;

        // channel text
        t = StaticText.new(
            w, Rect(header_x + (~local_offsetx*idx), text_ypos, 58, 20)).string_(idx.asString).align_(\left);
        t.font = Font(~textfont, 11);
        t.stringColor_(~textcol);
    };

    // draw rows
    ~darkrow = Color(0.8, 0.8, 0.8, 1.0);
    ~lightrow = Color(0.84, 0.84, 0.84, 1.0);
    ~color_list = [~lightrow, ~darkrow];

    ~num_rows.do { |jdx|

        var this_idx = 0;
        this_idx = ((jdx % 4) < 1).asInteger;
        ~color_list[this_idx].setFill;

        // draw rects
        ~num_tracks.do { |idx|
            var startx = 60;
            Pen.addRect(Rect(startx + (~local_offsetx*idx), ~ypos + (~lineheight*jdx), 86, 18));
            Pen.fill;
        };

        // draw row numbers
        t = StaticText.new(
            w, Rect(-4, ~ypos + (~lineheight*jdx), 58, 20)).string_(jdx.asString).align_(\right);
        t.font = Font(~textfont, 13);
        t.stringColor_(~textcol);

    };

    // draw highlight cell
    Color(0.6, 0.8, 0.88, 1.0).setFill;
    Pen.addRect(
         Rect(60 + (~local_offsetx*~cursor_cell[0]),
    ~ypos + (~lineheight*~cursor_cell[1]), 86, 18));
    Pen.fill;

};
//
// q = CompositeView.new(w, Rect(0, 0, 1220, 390));
// q.enabled = true;
// // q.drawFunc = {|mee|
// //     Color(0.6, 0.8, 0.88, 1.0).setFill;
// //     Pen.addRect(
// //         Rect(60 + (~local_offsetx*~cursor_cell[0]),
// //     ~ypos + (~lineheight*~cursor_cell[1]), 86, 18));
// //     Pen.fill;
// // };



w.view.keyDownAction = {
    arg view, char, modifiers, unicode, keycode;
    [keycode].postln; //, modifiers, unicode].postln;
    ~cursor_highlight.value(keycode);
	w.clear;
    w.refresh;
    Synth(\hatsound, [\freq, 1220, \dur, 0.01]);


};
w.front;
)

// execute second
(
w.front; // something safe to type on
//{ SinOsc.ar(800, 0, KeyState.kr(38, 0, 0.1)) }.play;
)

w.close;

