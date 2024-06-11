const controlXYArray = document.querySelectorAll('.controlXY');
for (const controlXY of controlXYArray) {
    controlXY.onpointermove = function(e){
	    if(e.buttons) {
			const parent = document.getElementById('my_xy');
			const maxX = parent.clientWidth - 300;
			const maxY = parent.clientHeight - 100;

			const id = this.id;
			const number = id.replace('my_xy', '');

			const x = this.offsetLeft + e.movementX;
			if (x >= 0 && x <= maxX) {
				document.getElementById('my_x' + number).value = x;
				this.style.left = x + 'px';
			}

			const y = this.offsetTop  + e.movementY;
			if (y >= 0 && y <= maxY) {
				document.getElementById('my_y' + number).value = y;
				this.style.top = y + 'px';
			}

	        this.draggable      = false;
	        this.setPointerCapture(event.pointerId);
	    }
	};
}

// 初期設定値をDBから読み込んで配列としてサーブレットから連携？（画像２が設定済の時の例）
const xyArray = [{ x:-1, y:-1 }, { x:50, y:50 }, { x:-1, y:-1 }];
// 初期設定値をレイアウト
for (let i = 0; i < xyArray.length; i++) {
	const xyData = xyArray[i];
	if (xyData.x > 0 && xyData.y > 0) {
		const number = (i + 1);
		const target = document.getElementById('my_xy' + number);
		document.getElementById('my_x' + number).value = xyData.x;
		target.style.left = xyData.x + 'px';
		document.getElementById('my_y' + number).value = xyData.y;
		target.style.top = xyData.y + 'px';
	}
}


