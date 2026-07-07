import { useEffect, useMemo, useState } from 'react'
import api from '../services/api'
import type { Squadra } from '../types'

function SquadrePage() {
    const [squadre, setSquadre] = useState<Squadra[]>([])
    const [loading, setLoading] = useState(true)
    const [errore, setErrore] = useState<string | null>(null)
    const [ricerca, setRicerca] = useState('')

    const backendBaseUrl = import.meta.env.DEV
        ? `http://${window.location.hostname}:8080`
        : ''

    useEffect(() => {
        async function caricaSquadre() {
            try {
                setLoading(true)
                setErrore(null)

                const response = await api.get<Squadra[]>('/api/squadre')
                setSquadre(response.data)
            } catch {
                setErrore('Errore durante il caricamento delle squadre.')
            } finally {
                setLoading(false)
            }
        }

        caricaSquadre()
    }, [])

    const squadreFiltrate = useMemo(() => {
        const testoRicerca = ricerca.trim().toLowerCase()

        if (testoRicerca === '') {
            return squadre
        }

        return squadre.filter((squadra) =>
            squadra.nome.toLowerCase().includes(testoRicerca)
        )
    }, [squadre, ricerca])

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
                            <a href={`${backendBaseUrl}/squadre`} className="back-link">
                                ← Torna alla lista classica
                            </a>
                        </div>

                        <div className="list-page-header">
                            <div>
                                <span className="eyebrow">Squadre</span>
                                <h1>Squadre SIW Calcio</h1>
                                <p>Consulta le squadre registrate e scopri i giocatori che ne fanno parte.</p>
                            </div>
                        </div>

                        <div className="search-card">
                            <label htmlFor="ricercaSquadra">Cerca squadra</label>

                            <input
                                id="ricercaSquadra"
                                type="text"
                                value={ricerca}
                                onChange={(event) => setRicerca(event.target.value)}
                                autoComplete="off"
                            />

                            <p>
                                Squadre visualizzate: <strong>{squadreFiltrate.length}</strong>
                            </p>
                        </div>

                        {loading && (
                            <div className="empty-state">
                                Caricamento squadre...
                            </div>
                        )}

                        {!loading && errore && (
                            <div className="empty-state error-state">
                                {errore}
                            </div>
                        )}

                        {!loading && !errore && squadreFiltrate.length === 0 && (
                            <div className="empty-state">
                                Nessuna squadra trovata.
                            </div>
                        )}

                        {!loading && !errore && squadreFiltrate.length > 0 && (
                            <div className="list-card">
                                <ul className="items-list">
                                    {squadreFiltrate.map((squadra) => (
                                        <li key={squadra.id}>
                                            <a
                                                href={`${backendBaseUrl}/squadre/${squadra.id}`}
                                                className="item-link"
                                            >
                                                <span>{squadra.nome}</span>
                                                <span className="item-arrow">→</span>
                                            </a>
                                        </li>
                                    ))}
                                </ul>
                            </div>
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

export default SquadrePage