s.boot;

// execute first

(

~cursor_cell = [0, 0];

~cursor_highlight = {
	| keycode |
	/*
	38 up
	40 down
	37 left
	39 right
	*/
	switch (keycode,

	  39, {	~cursor_cell[0] = ~cursor_cell[0] + 1 },
	  37, {	~cursor_cell[0] = ~cursor_cell[0] - 1 },
	  38, {	~cursor_cell[1] = ~cursor_cell[1] - 1 },
	  40, {	~cursor_cell[1] = ~cursor_cell[1] + 1 }
	);
	~cursor_cell[0] = ~cursor_cell[0] % 12;
	~cursor_cell[1] = ~cursor_cell[1] % 32;
	~cursor_cell.postln;

};


GUI.qt;

Window.closeAll;

w = Window.new("ptrk", Rect.new(340, 330, 1560, 720))
.front
.alwaysOnTop_(true);

w.drawFunc_{|me|

	~ypos = 43;
	~lineheight = 20;
	~textfont = "Consolas";
	~textcol = Color(0.6, 0.6, 0.6, 0.4);
    ~num_tracks = 12;
    ~local_offsetx = 90;

	// draw header
    ~num_tracks.do { |idx|
        var offsetx = 90;
		var header_x = 95;
		var text_ypos = 22;

    	// channel text
		t = StaticText.new(w, Rect(header_x + (~local_offsetx*idx), text_ypos, 58, 20)).string_(idx.asString).align_(\left);
        t.font = Font(~textfont, 11);
        t.stringColor_(~textcol);
	};

	// draw highlight cell

	Color(0.3, 0.8, 0.8, 0.4).setFill;
	Pen.addRect(Rect(60 + (~local_offsetx*~cursor_cell[0]), ~ypos + (~lineheight*~cursor_cell[1]), 86, 18));
	Pen.fill;

	// draw rows
	~darkrow = Color(0.6, 0.6, 0.6, 0.1);
	~lightrow = Color(0.7, 0.7, 0.7, 0.1);
	~color_list = [~lightrow, ~darkrow];

	32.do { |jdx|

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
		t = StaticText.new(w, Rect(-4, ~ypos + (~lineheight*jdx), 58, 20)).string_(jdx.asString).align_(\right);
        t.font = Font(~textfont, 13);
        t.stringColor_(~textcol);

	};


};

w.view.keyDownAction = {
	arg view, char, modifiers, unicode, keycode;
	[keycode].postln; //, modifiers, unicode].postln;
	~cursor_highlight.value(keycode);
	// w.refresh;
};
w.front;
)

// execute second
(
w.front; // something safe to type on
{ SinOsc.ar(800, 0, KeyState.kr(38, 0, 0.1)) }.play;
)

w.close;

