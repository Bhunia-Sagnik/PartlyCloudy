	var weatherIcon = document.getElementById("weather-icon");
	
	var val = document.getElementById("wc").value;
	  switch (val) {
      case 'Clouds':
          weatherIcon.src = "images/cloud.png";
          break;
      case 'Clear':
          weatherIcon.src = "images/sunny.png";
          break;
      case 'Rain':
          weatherIcon.src = "images/rain.png";
          break;
      case 'Mist':
          weatherIcon.src = "images/mist.png";
          break;
      case 'Snow':
          weatherIcon.src = "images/snow.png";
          break;
      case 'Haze':
          weatherIcon.src = "images/haze.png";
          break;
  }
  
function updateDigitalClock() {
    const now = new Date();
    const hours = String(now.getHours()).padStart(2, '0');
    const minutes = String(now.getMinutes()).padStart(2, '0');
    const seconds = String(now.getSeconds()).padStart(2, '0');
    document.getElementById('digital-clock').textContent = `${hours}:${minutes}:${seconds}`;
}

setInterval(updateDigitalClock, 1000);
updateDigitalClock();
