
fetchGrid()
setInterval(() => {
    getUpdate()
}, 5000)

$('#formula').on('keypress', function (e) {
    if(e.which === 13){
        postFormula($('#formula').val());
    }

});

$(document).keydown(function (e) {
    let isEscape;
    if ("key" in e) {
        isEscape = (e.key === "Escape" || e.key === "Esc");
    } else {
        isEscape = (e.keyCode === 27);
    }
    if (isEscape) {
        clearFocus();
    }
});

document.body.addEventListener("click", (e) => {
    clearFocus();
})

let alphabet =  "abcdefghijklmnopqrstuvwxyz";
let cellIdSelected = null;
let cells = null;
let lastTimestamp = new Date().toISOString();

function fetchGrid() {
    $.get(window.location.origin+ '/assignment_3_war_exploded/grid', data => {
        let nColumns = data.grid.nColumns;
        let nRows = data.grid.nRows;
        cells = data.cells;

        // Add Headers
        let table = $('<table id="root">');
        table.append($('<tr>'));
        table.append('<th></th>');
        for (c = 0; c < nColumns; c++) {
            table.append('<th>'+alphabet[c].toUpperCase()+'</th>');
        }
        table.append($('</tr>'));
        // Add Content
        for (i = 0; i < nRows; i++) {
            table.append($('<tr>'));
            table.append('<th>'+(i+1)+'</th>');
            for (c = 0; c < nColumns; c++) {
                let cell = cells[alphabet[c].toString().toUpperCase() + (i+1).toString()];
                table.append('<td id=\"'+cell.id+'\">'+cell.value+'</td>');
            }
            table.append($('</tr>'));
        }
        $('#root').remove();
        $('body').append(table);
        $('td').click(listenToCellClick);
        $('#'+cellIdSelected).addClass('focus');
    }, 'json')
}

function postFormula(formula) {
    $.ajax({
        url: window.location.origin+ '/assignment_3_war_exploded/formula',
        type: 'POST',
        dataType: 'json',
        data: JSON.stringify({
            cellId: cellIdSelected,
            formula: formula
        }),
        contentType: "application/json",
        success: data => {
            updateChangedCells(data.changedCells);
            clearFocus();
        }
    })
}

function updateChangedCells(changedCells) {
    for(const changedCell of changedCells) {
        cells[changedCell.id] = changedCell;
        $('#'+changedCell.id).html(changedCell.value);
    }
}

function getUpdate() {
    $.ajax({
        url: window.location.origin+ '/assignment_3_war_exploded/update',
        type: 'GET',
        dataType: 'json',
        data: {
            timestamp: lastTimestamp
        },
        success: data => {
            lastTimestamp = new Date().toISOString();
            for (const record of data) {
                updateChangedCells(record.cells)
            }
        }
    })
}

function listenToCellClick(e) {
    e.stopPropagation();
    if (cellIdSelected != null)
        $('#'+cellIdSelected).html(getCell(cellIdSelected).value);
    cellIdSelected = e.target.id;
    $('#'+cellIdSelected).html(getCell(cellIdSelected).formula);
    $('td').removeClass('focus');
    $('#formula').val(getCell(cellIdSelected).formula);
    $('#formula').removeAttr("disabled");
    $(this).addClass('focus');
}

function clearFocus() {
    $('#'+cellIdSelected).removeClass("focus");
    $('#formula').attr("disabled", true);
    $('#formula').val("");
    let cell = getCell(cellIdSelected);
    if (cell != null)
        $('#'+cellIdSelected).html(cell.value);
    cellIdSelected = null;
}

function getCell(cellId) {
    return cells[cellId]
}