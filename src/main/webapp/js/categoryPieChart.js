document.addEventListener('DOMContentLoaded', () => {
  const ctxPie = document.getElementById('categoryPieChart').getContext('2d');

  // dashboard.jsp側で <script> 内に以下のようにJS変数を埋め込んでおく必要があります
  // const categoryLabels = [...];
  // const categoryData = [...];

  const categoryPieChart = new Chart(ctxPie, {
    type: 'pie',
    data: {
      labels: categoryLabels,
      datasets: [{
        label: '売上割合',
        data: categoryData,
        backgroundColor: [
          'rgba(255, 99, 132, 0.7)',
          'rgba(54, 162, 235, 0.7)',
          'rgba(255, 206, 86, 0.7)',
          'rgba(75, 192, 192, 0.7)',
          'rgba(153, 102, 255, 0.7)',
          'rgba(255, 159, 64, 0.7)'
        ],
        borderWidth: 1
      }]
    },
    options: {
      responsive: true
    }
  });
});