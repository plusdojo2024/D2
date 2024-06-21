function makeTransparent(imgElement) {
    // 半透明
    imgElement.style.opacity = 0.5;

    // 確認ダイアログ
    var result = confirm("おわりましたか？");
    if (!result) {
        // キャンセルを選択した場合は透明度を元に戻す
        imgElement.style.opacity = 1.0;
    }
    // 「OK」の場合は何もしない（既に透明化済み）
}


