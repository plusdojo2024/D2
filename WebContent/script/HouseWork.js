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

// ボタン、モダル、モダルの閉じるボタン、オーバーレイを変数に格納
const btn = document.querySelector('.btn');
const modal = document.querySelector('.modal');
const closeBtn = document.querySelector('.close');
const overlay = document.querySelector('.overlay');

// ボタンをクリックしたら、モダルとオーバーレイに.activeを付ける
btn.addEventListener('click', function(e){
  // aタグのデフォルトの機能を停止する
  e.preventDefault();
  // モーダルとオーバーレイにactiveクラスを付与する
  modal.classList.add('active');
  overlay.classList.add('active');
});

// モダルの閉じるボタンをクリックしたら、モダルとオーバーレイのactiveクラスを外す
closeBtn.addEventListener('click', function(){
  modal.classList.remove('active');
  overlay.classList.remove('active');
});

// オーバーレイをクリックしたら、モダルとオーバーレイのactiveクラスを外す
overlay.addEventListener('click', function() {
  modal.classList.remove('active');
  overlay.classList.remove('active');
});
