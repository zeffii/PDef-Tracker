// pattern data
~num_cols = 4;
~num_rows = 16;
~pattern_matrix = Array2D.new(~num_cols, ~num_rows);

~pattern_data = { | num_cols, num_rows |

    // " NNN DD VV AA XXYY "
    ~mlist = List[\NNN,"...",\DD,"..",\VV,"..",\AA,"..",\XXYY,"...."];
    num_rows.do { |jdx|
        num_cols.do { |idx|
            ~pattern_matrix[idx, jdx] = Dictionary.newFrom(~mlist);
        };
    };


};

~pattern_data.value(~num_cols, ~num_rows);

~cell_empty = ~pattern_matrix[1, 2];
~cell_empty[\NNN] = "C-5";
~cell_empty[\DD] = "03";
~cell_empty[\VV] = "FA";
~cell_empty[\AA] = "10";
~cell_empty[\XXYY] = "8000";

~cell_empty2 = ~pattern_matrix[0, 3];
~cell_empty2[\NNN] = "E-5";
~cell_empty2[\DD] = "02";
~cell_empty2[\VV] = "6A";
~cell_empty2[\AA] = "05";
~cell_empty2[\XXYY] = "30A0";

~cell_empty2 = ~pattern_matrix[0, 6];
~cell_empty2[\NNN] = "^^^";