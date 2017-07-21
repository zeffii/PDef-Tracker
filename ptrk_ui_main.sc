s.boot;

// execute first
/*
38 up
40 down
37 left
39 right
*/
(

GUI.qt;

Window.closeAll;

w = Window.new("PDef Tracker", Rect.new(340, 170, 1120, 770))
.front
.alwaysOnTop_(true);

w.drawFunc_{|me|

	~textfont = "Consolas";
	~textcol = Color(0.6, 0.6, 0.6, 0.4);

	// draw header
    6.do { |idx|
        var offsetx = 90;
		var header_x = 95;
		var text_ypos = 22;

    	// channel text
		t = StaticText.new(w, Rect(header_x + (offsetx*idx), text_ypos, 58, 20)).string_(idx.asString).align_(\left);
        t.font = Font(~textfont, 11);
        t.stringColor_(~textcol);
	};

	// draw rows
	~darkrow = Color(0.6, 0.6, 0.6, 0.1);
	~lightrow = Color(0.8, 0.8, 0.8, 0.1);
	~darkrow.setFill;

	32.do { |jdx|

		~lineheight = 20;
		~ypos = 43;

		// draw rects
		6.do { |idx|
			var startx = 60;
			var offsetx = 90;

			Pen.addRect(Rect(60 + (offsetx*idx), ~ypos + (~lineheight*jdx), 86, 18));
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
	[char, keycode].postln; //, modifiers, unicode].postln;
};
//w.front;
)

// execute second
(
w.front; // something safe to type on
{ SinOsc.ar(800, 0, KeyState.kr(38, 0, 0.1)) }.play;
)

w.close;
