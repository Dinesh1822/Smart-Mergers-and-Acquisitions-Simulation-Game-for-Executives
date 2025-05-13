🧠 Project Name:
Smart Mergers & Acquisitions (M&A) Simulation Game for Executives

🎯 Objective:
This is a decision-making simulation game where the player acts as a high-level executive making strategic M&A (merger and acquisition) decisions. The goal is to build a strong portfolio of companies by smartly investing within a fixed budget, considering the growth potential and market share of target companies.

🏗️ How It Works:
1. Company Class
Each company has:

name: The company’s identity.

value: Cost to acquire.

growth_rate: The company's annual growth rate.

market_share: Its share in the industry.

It includes a method to simulate growth over time (simulate_growth), which increases both its value and market share.

2. Executive Class
Each player has:

name: The executive’s name.

budget: A fixed fund (e.g., $2000M) to spend on acquisitions.

portfolio: A list of acquired companies.

It includes logic for:

Acquiring companies (if budget allows)

Displaying a summary of acquisitions

3. Gameplay Loop
The game has 3 rounds.

In each round:

The game presents 3 randomly generated companies.

The player selects one to acquire.

The acquired company is added to the portfolio.

All companies in the portfolio simulate one year of growth.

4. End of Game
After 3 rounds, the game prints:

A list of all acquired companies

Each company's updated value and market share

The total portfolio value and remaining budget
