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