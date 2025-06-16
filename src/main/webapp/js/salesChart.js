document.addEventListener('DOMContentLoaded', () => {
    const salesAggregated = {};
	
	//「単価 × 数量」を計算して売上金額を算出
    salesData.forEach(sale => {
        const amount = sale.unitPrice * sale.saleNumber;
		
		//もし既にその日付の合計があれば、今回の金額を加算
        if (salesAggregated[sale.saleDate]) {
            salesAggregated[sale.saleDate] += amount;
        } else {//なければ、その日付の合計を今回の金額で初期化
            salesAggregated[sale.saleDate] = amount;
        }
    });

    const labels = Object.keys(salesAggregated).sort();//横軸（日付）
    const data = labels.map(date => salesAggregated[date]);//縦軸（売上データ）

    const ctx = document.getElementById('saleChart').getContext('2d');
    new Chart(ctx, {
        type: 'line',//線グラフ
        data: {
            labels,//日付
            datasets: [{//縦軸のデータセット
                label: '売上金額',//凡例の名前
                data,//縦軸の値
                borderColor: 'rgba(75, 192, 192, 1)',
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                fill: true,//線の下を塗りつぶす
                tension: 0.3//なめらかな曲線にする
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true,//Y軸を０から始める
                    ticks: {
                        callback: value => value.toLocaleString()//数値をカンマ区切りに
                    }
                }
            },
            plugins: {
                legend: { display: true }//凡例表示のON・OFF//falseにすれば消える
            }
        }
    });
});
