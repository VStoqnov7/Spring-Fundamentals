const boxImgA = document.getElementById('box-a-img');
const boxImgB = document.getElementById('box-b-img');
const boxTempA = document.getElementById('box-a-temp');
const boxTempB = document.getElementById('box-b-temp');

fetch("https://api.openweathermap.org/data/2.5/weather?lat=42.698334&lon=23.319941&appid=fb8e73a8a30f29a82c9f5b26f686eec1")
    .then(data=> data.json())
    .then(info => {
        boxTempA.innerText = Math.round(info.main.temp - 273.15);
        boxImgA.src = '/images/weather-icons/' + info.weather[0].icon + '.png';
    });

fetch("https://api.openweathermap.org/data/2.5/weather?lat=42.41736760&lon=27.69635640&appid=fb8e73a8a30f29a82c9f5b26f686eec1")
    .then(data=> data.json())
    .then(info => {
        boxTempB.innerText = Math.round(info.main.temp - 273.15);
        boxImgB.src = '/images/weather-icons/' + info.weather[0].icon + '.png';
    });