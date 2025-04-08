<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Accueil</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style-common.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style-home.css">
</head>
<body>
    <div class="container">
        <!-- Barre de navigation -->
        <nav class="navbar">
            <div class="logo">
                <h2>Accueil</h2>
            </div>
            <ul class="nav-links">
                <li><a href="${pageContext.request.contextPath}/Accueil.jsp" class="active">Accueil</a></li>
                <li><a href="${pageContext.request.contextPath}/formCredit.jsp" class="active">Credit</a></li>
                <li><a href="${pageContext.request.contextPath}/depense" class="active">Depense</a></li>
                <li><a href="${pageContext.request.contextPath}/dashboard" class="active">Dashboard</a></li>
            </ul>
        </nav>
        
        <%-- <!-- Contenu principal -->
        <div class="main-content">
            <h1>Bienvenue, ${userName}!</h1>
            
            <!-- Résumé -->
            <div class="dashboard-summary">
                <div class="summary-card total-accounts">
                    <div class="card-icon">
                        <i class="icon-accounts"></i>
                    </div>
                    <div class="card-content">
                        <h3>Total des comptes</h3>
                        <p class="card-value">${comptes.size()}</p>
                    </div>
                </div>
                
                <div class="summary-card total-balance">
                    <div class="card-icon">
                        <i class="icon-balance"></i>
                    </div>
                    <div class="card-content">
                        <h3>Solde total</h3>
                        <p class="card-value">${soldeTotalFormate} €</p>
                    </div>
                </div>
                
                <div class="summary-card recent-transactions">
                    <div class="card-icon">
                        <i class="icon-transactions"></i>
                    </div>
                    <div class="card-content">
                        <h3>Dernières transactions</h3>
                        <p class="card-value">${recentMovements.size()}</p>
                    </div>
                </div>
            </div> --%>
            
            <!-- Actions rapides -->
            <div class="quick-actions">
                <h2>Actions rapides</h2>
                <div class="actions-grid">
                        <a href="${pageContext.request.contextPath}/formCredit.jsp" class="action-card">
                            <div class="action-icon">
                                <i class="icon-deposit"></i>
                            </div>
                            <div class="action-content">
                                <h3>Ajouter une Ligne de Credit</h3>
                                <p>Effectuer un Credit</p>
                            </div>
                        </a>

                        <a href="${pageContext.request.contextPath}/depense" class="action-card">
                            <div class="action-icon">
                                <i class="icon-deposit"></i>
                            </div>
                            <div class="action-content">
                                <h3>Ajouter une Ligne de Depense</h3>
                                <p>Effectuer un Depense</p>
                            </div>
                        </a>
        
                </div>
            </div>
        </div>
    </div>
</body>
</html>