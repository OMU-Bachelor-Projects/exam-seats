from flask import Flask
from flask.templating import render_template

app = Flask(__name__)

@app.route("/results")
def results():
    return render_template('results.html')

@app.route("/")
def index():
    return render_template('index.html')