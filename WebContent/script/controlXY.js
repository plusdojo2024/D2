const controlXYArray = document.querySelectorAll('.controlXY');
for (const controlXY of controlXYArray) {
    controlXY.onpointermove = function(e){
     // マウスボタンが押されている場合の処理
	    if(e.buttons) {
	    // 親要素のサイズから計算された最大の移動可能範囲を設定する
			const parent = document.getElementById('my_xy');
			const maxX = parent.clientWidth - 300;
			const maxY = parent.clientHeight - 100;

			// 現在の要素のIDから番号を取得する
			const id = this.id;
			const number = id.replace('my_xy', '');

			 // X 座標の計算と設定
			const x = this.offsetLeft + e.movementX;
			if (x >= 0 && x <= maxX) {
				document.getElementById('my_x' + number).value = x;
				this.style.left = x + 'px';
			}
			 // Y 座標の計算と設定
			const y = this.offsetTop  + e.movementY;
			if (y >= 0 && y <= maxY) {
				document.getElementById('my_y' + number).value = y;
				this.style.top = y + 'px';
			}

			// ドラッグ可能な状態を解除し、ポインタのキャプチャを設定する
	        this.draggable      = false;
	        this.setPointerCapture(event.pointerId);
	    }
	};
}

// 初期設定値をDBから読み込んで配列としてサーブレットから連携？（画像２が設定済の時の例）
//const xyArray = [{ x:1000, y:50 }, { x:1100, y:50 }, { x:1000, y:150 }, { x:1100, y:150 }, { x:1000, y:250 }, { x:1100, y:250 }];
// 初期設定値をレイアウトするためのループ
// xyArray から取得した初期設定値を設定する
for (let i = 0; i < xyArray.length; i++) {
	const xyData = xyArray[i];
	if (xyData.x > 0 && xyData.y > 0) {
		const number = (i + 1);
		const target = document.getElementById('my_xy' + number);
		 // X 座標と Y 座標を設定
		document.getElementById('my_x' + number).value = xyData.x;
		target.style.left = xyData.x + 'px';
		document.getElementById('my_y' + number).value = xyData.y;
		target.style.top = xyData.y + 'px';
	}
}


