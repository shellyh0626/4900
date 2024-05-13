var canvas = document.getElementById("canvas")
var ctx = canvas.getContext("2d")
canvas.width = window.innerWidth
canvas.height = window.innerHeight
var meteors = [] 
var allstar = 40 
function init() { 
    for (var i = 0; i < allstar; i++) {
        newmeteor() 
    }
}
function newmeteor(){
    meteors.push({
        lines: [{ 
            x: parseInt(Math.random() * canvas.width),
            y: parseInt(Math.random() * canvas.height * 0.7),
        }],
        life: parseInt(Math.random() * 100) + 100,
        age: 0 
    })
}
function draw() {
    ctx.clearRect(0, 0, canvas.width, canvas.height)
    for (var i = 0; i < meteors.length; i++) { 
        meteor = meteors[i] 
        lines = meteor.lines 
        for (var j = 0; j < lines.length; j++) { 
            ctx.fillStyle = "rgba(255,255,255," + j / lines.length + ")" 
            ctx.fillRect(lines[j].x, lines[j].y, 1, 1) 
        }
        ctx.fillStyle = "yellow" 
        var star = lines[lines.length-1] 
        ctx.fillRect(star.x, star.y, 2, 2) 
        if (meteor.age <= meteor.life / 2) { 
            lines.push({ 
                x: star.x+1 , 
                y: star.y+0.3 
            })
        }
        else { 
            lines.shift()
        }
        meteor.age++ 
        if (meteor.age >= meteor.life) {
            meteors.splice(i, 1)
            newmeteor()
        }

    }
}
init()
setInterval(draw, 1)