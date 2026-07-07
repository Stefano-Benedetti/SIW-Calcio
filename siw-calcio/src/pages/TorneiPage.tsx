import { useEffect, useMemo, useState } from 'react'
import api from '../services/api.ts'
import type { Torneo } from '../types'
import '../App.css'

function TorneiPage() {
    const [tornei, setTornei] = useState<Torneo[]>([])
    const [loading, setLoading] = useState(true)
    const [errore, setErrore] = useState<string | null>(null)
    const [annoFiltro, setAnnoFiltro] = useState('tutti')

    const backendBaseUrl = import.meta.env.DEV
        ? `http://${window.location.hostname}:8080`
        : ''

    useEffect(() => {
        async function caricaTornei() {
            try {
                setLoading(true)
                setErrore(null)

                const response = await api.get<Torneo[]>('/api/tornei')
                setTornei(response.data)
            } catch {
                setErrore('Errore durante il caricamento dei tornei.')
            } finally {
                setLoading(false)
            }
        }

        caricaTornei()
    }, [])

    const anniDisponibili = useMemo(() => {
        const anni = tornei.map((torneo) => torneo.anno)
        return Array.from(new Set(anni)).sort((a, b) => b - a)
    }, [tornei])

    const torneiFiltrati = useMemo(() => {
        if (annoFiltro === 'tutti') {
            return tornei
        }

        return tornei.filter((torneo) => torneo.anno === Number(annoFiltro))
    }, [tornei, annoFiltro])

    return (
        <>
            <header className="site-header">
        <div className="container header-content">
        <a href={`${backendBaseUrl}/`} className="logo">
        SIW Calcio
    </a>

    <nav className="main-nav">
    <a href={`${backendBaseUrl}/tornei`}>Tornei</a>
    <a href={`${backendBaseUrl}/squadre`}>Squadre</a>
    <a href={`${backendBaseUrl}/login`}>Login</a>
    <a href={`${backendBaseUrl}/register`} className="btn btn-small">
        Registrazione
        </a>
        </nav>
        </div>
        </header>

        <main className="list-main">
    <section className="list-section">
    <div className="container">
    <div className="page-actions">
    <a href={`${backendBaseUrl}/tornei`} className="back-link">
                  ← Torna alla lista classica
    </a>
    </div>

    <div className="list-page-header">
    <div>
        <span className="eyebrow">Tornei</span>
        <h1>Elenco tornei</h1>
    <p>Consulta i tornei pubblicati da SIW Calcio.</p>
    </div>

    <div className="react-filter">
    <label htmlFor="annoFiltro">Anno</label>

        <select
    id="annoFiltro"
    value={annoFiltro}
    onChange={(event) => setAnnoFiltro(event.target.value)}
>
    <option value="tutti">Tutti</option>

    {anniDisponibili.map((anno) => (
        <option key={anno} value={String(anno)}>
        {anno}
        </option>
    ))}
    </select>
    </div>
    </div>

    {loading && (
        <div className="empty-state">
            Caricamento tornei...
        </div>
    )}

    {!loading && errore && (
        <div className="empty-state error-state">
            {errore}
            </div>
    )}

    {!loading && !errore && torneiFiltrati.length === 0 && (
        <div className="empty-state">
            Non è stato registrato alcun torneo per l’anno selezionato.
    </div>
    )}

    {!loading && !errore && torneiFiltrati.length > 0 && (
        <>
            <div className="react-list-info">
            Tornei visualizzati: <strong>{torneiFiltrati.length}</strong>
    </div>

    <div className="list-card">
    <ul className="items-list">
        {torneiFiltrati.map((torneo) => (
                <li key={torneo.id}>
                <a
                    href={`${backendBaseUrl}/tornei/${torneo.id}`}
        className="item-link"
            >
            <span>
                {torneo.nome} {torneo.anno}
        </span>

        <span className="item-arrow">→</span>
    </a>
    </li>
    ))}
        </ul>
        </div>
        </>
    )}
    </div>
    </section>
    </main>

    <footer className="site-footer">
    <div className="container">
        <p>SIW Calcio</p>
    </div>
    </footer>
    </>
)
}

export default TorneiPage